class Bob

  RESPONSES = {
    shouting: "Woah, chill out!",
    question: "Sure.",
    empty:    "Fine. Be that way!",
    gibberish: "Whatever."
  }

  def hey(string)
    comment = Comment.new(string)

    RESPONSES[comment.type]
  end
end

class Comment

  def initialize(comment)
    @comment = comment
  end

  def type
    if is_shouting?(@comment)
      :shouting
    elsif is_question?(@comment)
      :question
    elsif is_empty?(@comment)
      :empty
    else
      :gibberish
    end
  end

  private

  def is_shouting?(string)
    string == string.upcase && string.match(/[A-Za-z]/)
  end

  def is_question?(string)
    string[-1] == ("?")
  end

  def is_empty?(string)
    string.strip.empty?
  end
end
