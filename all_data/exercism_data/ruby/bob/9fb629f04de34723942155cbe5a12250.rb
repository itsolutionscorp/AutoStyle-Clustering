class Message < String
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
  def hey (sentence)
    message = Message.new sentence.to_s

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
