package oocl.todolistbackend.dto;

public class TodoItemReq {
    private String text;

    public TodoItemReq(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
