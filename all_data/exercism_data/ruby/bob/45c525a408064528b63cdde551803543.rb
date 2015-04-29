class Bob
  def hey(str)
    message = Message.new str
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

class Message < String
  def initialize(obj)
    super(obj.to_s)
  end

  def silence?
    strip == ''
  end

  def shouting?
    upcase == self
  end

  def question?
    end_with? '?'
  end
end
