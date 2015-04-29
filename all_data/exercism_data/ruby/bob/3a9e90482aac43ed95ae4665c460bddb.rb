class Bob
  def hey(text)
    message = Message.new text
    case
    when message.silence?
      'Fine. Be that way!'
    when message.shouting?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(text)
    @text = text.to_s
  end

  def silence?
    @text.strip == ''
  end

  def shouting?
    @text.upcase == @text
  end

  def question?
    @text.end_with? '?'
  end
end
