class Bob
  def hey message
    message = Message.new(message.to_s)
    if message.empty?
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
  def loud?
    self == self.upcase
  end

  def question?
    self.end_with? '?'
  end
end
