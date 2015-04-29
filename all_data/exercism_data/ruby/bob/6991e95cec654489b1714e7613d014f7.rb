class Bob
  def hey message
    message = Message.new(message.to_s)
    case
    when message.silent? then 'Fine. Be that way!'
    when message.loud? then 'Woah, chill out!'
    when message.question? then 'Sure.'
    else 'Whatever.'
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
