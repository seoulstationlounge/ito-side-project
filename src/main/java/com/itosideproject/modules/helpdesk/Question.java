package com.itosideproject.modules.helpdesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itosideproject.modules.account.Account;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Question extends AbstractEntity {

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private Account writer;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	@JsonProperty
	private Integer countOfAnswer = 0;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id DESC")
	private List<Answer> answers;
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(Account loginUser) {
		System.out.println("writer : " + writer);
		return this.writer.equals(loginUser);
	}
	
	public void addAnswer() {
		this.countOfAnswer += 1;
	}
	
	public void deleteAnswer() {
		this.countOfAnswer -= 1;
	}
}
