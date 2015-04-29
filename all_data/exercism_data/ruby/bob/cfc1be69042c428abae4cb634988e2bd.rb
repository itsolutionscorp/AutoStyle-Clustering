class Bob
  def hey message_text
    message = MessageParser.new message_text
    if message.is_silence?
      "Fine. Be that way."
    elsif message.is_shouting?
      "Woah, chill out!"
    elsif message.is_question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class MessageParser < Struct.new :message
  def is_shouting?
    message == message.upcase
  end

  def is_question?
    message.end_with?("?")
  end

  def is_silence?
    message.nil? || message.empty?
  end
end
