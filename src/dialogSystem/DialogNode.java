package dialogSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class DialogNode implements Serializable {
    private String question;
    private String hint;

    private ArrayList<String> triggers = new ArrayList<>();
    private ArrayList<DialogNode> responses = new ArrayList<>();
    private DialogNode defaultResponse = null;
    private boolean last = false;

    public DialogNode(String question){
        this.question = question;
        hint = "";
    }

    public DialogNode(String question,String hint){
        this.question = question;
        this.hint = hint;
    }

    public DialogNode(String question,boolean last){
        this.question = question;
        this.hint = "";
        this.last = last;
    }

    public DialogNode(String question,String hint,boolean last){
        this.question = question;
        this.hint = hint;
        this.last = last;
    }

    public void addTrigger(String trigger, DialogNode response) {
        triggers.add(trigger);
        responses.add(response);
    }

    public DialogNode getResponse(String trigger){
        int index = triggers.indexOf(trigger);
        if(index != -1){
            return responses.get(index);
        }
        return null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getTriggers() {
        return triggers;
    }

    public void setTriggers(ArrayList<String> triggers) {
        this.triggers = triggers;
    }

    public ArrayList<DialogNode> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<DialogNode> responses) {
        this.responses = responses;
    }

    public DialogNode getDefaultResponse() {
        return defaultResponse;
    }
    public void setDefaultResponse(DialogNode response){
        defaultResponse = response;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
