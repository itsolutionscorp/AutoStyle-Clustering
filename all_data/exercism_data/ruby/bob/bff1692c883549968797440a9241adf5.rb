class Bob

  ANSWERS = {
    question: "Sure.",
    yell: "Woah, chill out!",
    silence: "Fine. Be that way!",
  }

  DEFAULT_ANSWER = "Whatever."

  def hey s
    message = Message.new s
    ANSWERS.fetch(message.type, DEFAULT_ANSWER)
  end
end

class Message
  def initialize s
    @string = s.to_s
  end

  def yell?
    @string =~ /[[:alpha:]]/ and @string == @string.upcase
  end
  
  def question?
    @string.end_with?("?") and not yell?
  end

  def silence?
    @string.strip.empty?
  end

  def type
    case
    when question?
      :question
    when yell?
      :yell
    when silence?
      :silence
    else
      :other
    end
  end
end
