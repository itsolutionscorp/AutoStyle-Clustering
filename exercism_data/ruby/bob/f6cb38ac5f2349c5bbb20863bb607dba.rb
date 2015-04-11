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

    return 'Fine. Be that way.' if message.saying_nothing?
    return 'Woah, chill out!'   if message.yelling?
    return 'Sure.'              if message.asking?

    'Whatever.'
  end
end
