class Bob
  def hey message
    public_send("respond_to_#{MessageParser.new(message).get_type}")
  end

  def respond_to_silent
    "Fine. Be that way."
  end

  def respond_to_shouting
    "Woah, chill out!"
  end

  def respond_to_question
    "Sure."
  end

  def respond_to_normal
    "Whatever."
  end
end

class MessageParser < Struct.new(:message)
  def get_type
    [:silent, :shouting, :question].each do |method|
      return method if public_send(method)
    end
    return :normal
  end

  def shouting
    message == message.upcase
  end

  def question
    message.end_with?("?")
  end

  def silent
    message.nil? || message.empty?
  end
end
