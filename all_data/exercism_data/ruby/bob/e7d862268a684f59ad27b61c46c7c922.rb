class Bob

  @@answers = {
    question: "Sure.",
    yell: "Woah, chill out!",
    silence: "Fine. Be that way!",
    other: "Whatever."
  }

  def hey s
    return @@answers[Message.new(s).type]
  end
end

class Message
  def initialize s
    @string = s.to_s
  end

  def is_a_yell?
    @string =~ /[[:alpha:]]/ and @string == @string.upcase
  end
  
  def is_a_question?
    @string.end_with?("?") and not is_a_yell?
  end

  def is_a_silence?
    @string.strip.empty?
  end

  def type
    case
    when is_a_question?
      return :question
    when is_a_yell?
      return :yell
    when is_a_silence?
      return :silence
    else
      return :other
    end
  end
end
