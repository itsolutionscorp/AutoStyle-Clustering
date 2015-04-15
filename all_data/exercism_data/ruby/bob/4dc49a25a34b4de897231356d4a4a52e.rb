class Message < String
  def initialize(sentence)
    super sentence.to_s
  end

  def saying_nothing?
    empty?
  end

  def yelling?
    self == upcase
  end

  def asking?
    end_with? '?'
  end
end

class Bob
  def hey(sentence)
    message = Message.new sentence

    case
    when message.saying_nothing?
      'Fine. Be that way.'
    when message.yelling?
      'Woah, chill out!'
    when message.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
