package edu.vedev404.dosecerta.service;

import edu.vedev404.dosecerta.models.Medicamento;
import edu.vedev404.dosecerta.models.Usuario;
import edu.vedev404.dosecerta.repository.MedicamentoRepository;
import edu.vedev404.dosecerta.repository.UsuarioRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WhatsAppNotificationService {

    private final MedicamentoRepository medicamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestTemplate restTemplate;
    private static final String API_URL = "https://api.callmebot.com/whatsapp.php";

    public WhatsAppNotificationService(MedicamentoRepository medicamentoRepository,
                                       UsuarioRepository usuarioRepository,
                                       RestTemplate restTemplate) {
        this.medicamentoRepository = medicamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
    public void verificarEEnviarMensagens() {
        LocalTime agora = LocalTime.now().withSecond(0).withNano(0);
        System.out.println("🔍 Verificando horários às: " + agora);

        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Medicamento medicamento : medicamentos) {
            try {
                String hora = medicamento.getHora(); // Obtém a hora do medicamento
                LocalTime horaMedicamento = LocalTime.parse(hora, formatter); // Converte a hora do medicamento para LocalTime

                // Debug: Imprime as horas para comparação
//                System.out.println("hora: " + horaMedicamento + ", agora: " + agora);

                if (horaMedicamento.equals(agora)) {
//                    System.out.println("entrou...");
                    enviarMensagem(medicamento); // Envia a mensagem se for a hora certa
                }
            } catch (Exception e) {
                System.err.println("❌ Erro ao processar horário do medicamento: " + medicamento.getHora());
                e.printStackTrace(); // Imprime a pilha de exceções para depuração
            }
        }
    }

    private void enviarMensagem(Medicamento medicamento) {
        Long usuarioId = medicamento.getUsuarioId(); // Obtém o ID do usuário associado ao horário
        if (usuarioId == null) {
            System.err.println("❌ Erro: Usuário não encontrado para o horário ID: " + medicamento.getId());
            return;
        }

        // Busca o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            System.err.println("❌ Erro: Usuário não encontrado com ID: " + usuarioId);
            return;
        }

        String telefone = usuario.getTelefone();
        String apiKey = usuario.getMessageKey();
        String messageAlt = medicamento.getMessageAlt();

        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("❌ Erro: API Key do usuário está vazia ou nula!");
            return;
        }

        // Monta a mensagem a ser enviada
        String mensagem = (messageAlt != null && !messageAlt.trim().isEmpty())
                ? messageAlt
                : "💊 *Lembrete de Medicamento!*\n" +
                "📌 *Medicamento:* " + medicamento.getNomeMedicamento() + "\n" +
                "⏰ *Hora:* " + medicamento.getHora() + "\n" +
                "🔔 *Não se esqueça de tomar seu remédio!*";

        // Constrói a URL para a API do WhatsApp
        String url = API_URL + "?phone=" + telefone + "&text=" + mensagem + "&apikey=" + apiKey;

        System.out.println("📤 Enviando mensagem para: " + telefone);
        System.out.println("🔗 URL: " + url);

        // Envia a solicitação GET para a API do WhatsApp
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("✅ Resposta da API: " + response.getBody());
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar mensagem: " + e.getMessage());
        }
    }
}