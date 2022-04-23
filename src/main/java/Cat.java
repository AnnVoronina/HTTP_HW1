import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {
    private String id; // уникальный идентификатор записи
    private String text; //  сообщение
    private String type; //тип животного
    @JsonIgnore private String nickName;//кличка // @JsonIgnore для игнорирования поля при дессириализации
    private String user;// -имя пользователя
    private String upvotes;// голоса


    public Cat( // @JsonProperty указываем для дессириализации json понимает какие поля с какими в классе соотносятся
                @JsonProperty("id") String id,
                @JsonProperty("text") String text,
                @JsonProperty("type") String type,
                @JsonProperty("user") String user,
                @JsonProperty("upvotes") String upvotes)
                 {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Cat:\n" +
                "id:" + id +
                ",\n text: " + text +
                ",\n type: " + type +
                ",\n user: " + user +
                ",\n upvotes: " + upvotes;
    }
}
