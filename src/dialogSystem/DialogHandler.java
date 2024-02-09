package dialogSystem;

import java.io.Serializable;

public class DialogHandler implements Serializable {
    private DialogNode initialD;
    private DialogNode currentD;

    private boolean hasNext = true;
    public DialogHandler(DialogNode initialD){
        this.initialD = initialD;
        currentD = initialD;
    }
    public boolean trigger(String triggerString){
        DialogNode response = currentD.getResponse(triggerString);
        if(response != null){
            currentD = response;
            return true;
        }
        return false;
    }

    public void resetDialog(){
        currentD = initialD;
    }

    public String getQuestion(){
        return currentD.getQuestion();
    }

    public String getHint(){
        return currentD.getHint();
    }

    public boolean isLast(){
        return currentD.isLast();
    }
    public boolean hasNext(){
        boolean oldHasNext = hasNext;
        hasNext = !currentD.isLast();
        return oldHasNext;
    }
}
