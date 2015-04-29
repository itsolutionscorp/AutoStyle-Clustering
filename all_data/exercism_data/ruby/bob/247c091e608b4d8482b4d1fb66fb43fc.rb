class Bob
  def hey message
    case MessageType.new(message).call
    when :silent?
      "Fine. Be that way."
    when :shouting?
      "Woah, chill out!"
    when :question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class MessageType < Struct.new(:message)
  def call
    [:silent?, :shouting?, :question?].each do |method|
      return method if send(method)
    end
    return :normal
  end

  def shouting?
    message == message.upcase
  end

  def question?
    message.end_with?("?")
  end

  def silent?
    message.to_s.strip == ''
  end
end
