class String
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
    sentence = sentence.to_s

    return 'Fine. Be that way.' if sentence.saying_nothing?
    return 'Woah, chill out!'   if sentence.yelling?
    return 'Sure.'              if sentence.asking?

    'Whatever.'
  end
end
