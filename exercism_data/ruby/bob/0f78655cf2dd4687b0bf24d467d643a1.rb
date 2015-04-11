class Message < String
  def saying_nothing?
    self.empty?
  end

  def yelling?
    self.upcase == self
  end

  def asking?
    self.end_with? '?'
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
