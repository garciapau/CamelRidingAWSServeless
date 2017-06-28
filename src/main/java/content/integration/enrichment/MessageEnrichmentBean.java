package content.integration.enrichment;

public class MessageEnrichmentBean {

    public static String addJournalId(String custom) {
        return custom.replaceAll("<article ", "<article journalId=\"0000001\" ");
    }

    public static String addParent(String custom) {
        return custom
                .replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "")
                .replaceFirst("<article ", "<journal>\n<article ") + "\n</journal>";
    }
}
