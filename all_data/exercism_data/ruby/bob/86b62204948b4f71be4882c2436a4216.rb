class Bob
  def hey message_text
    message = MessageParser.new message_text
    if message.silence?
      "Fine. Be that way."
    elsif message.shouting?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class MessageParser < Struct.new :message
  def shouting?
    message == message.upcase
  end

  def question?
    message.end_with? "?"
  end

  def silence?
    message.nil? || message.empty?
  end
end
