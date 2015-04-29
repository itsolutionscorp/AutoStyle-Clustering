class Bob
  def hey(message)
    case Message.new(message).classify
    when :silence
      "Fine. Be that way!"
    when :question
      "Sure."
    when :shouting
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def classify
    if silence?
      :silence
    elsif shouting?
      :shouting
    elsif question?
      :question
    end
  end

  private

  def silence?
    @message.strip == ""
  end

  def shouting?
    @message.upcase == @message
  end

  def question?
    @message.end_with?("?")
  end
end
