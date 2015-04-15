class Bob
  RESPONSES = {
    nothing: "Fine. Be that way!",
    yell: "Woah, chill out!",
    question: "Sure."
  }

  def hey(message)
    message_type = MessageInterpreter.new(message).interpret
    response_for(message_type)
  end

  def response_for(message_type)
    RESPONSES.fetch(message_type, "Whatever.")
  end
end

class MessageInterpreter
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def interpret
    if nothing?
      :nothing
    elsif yell?
      :yell
    elsif question?
      :question
    else
      :default
    end
  end

  def nothing?
    message.strip.empty?
  end

  def question?
    message.end_with?("?")
  end

  def yell?
    message == message.upcase
  end
end
