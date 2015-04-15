class Bob
  def hey message
    message = Message.new(message.to_s)
    if message.silent?
      'Fine. Be that way!'
    elsif message.loud?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message < String
  def silent?
    strip.empty?
  end

  def loud?
    self == self.upcase
  end

  def question?
    end_with? '?'
  end
end
