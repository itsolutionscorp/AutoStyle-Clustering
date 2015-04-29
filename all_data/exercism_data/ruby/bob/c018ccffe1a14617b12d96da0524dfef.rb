class Bob
  # def initialize
  # end

  def hey(sentence)
    return 'Fine. Be that way!' if sentence.is_silence?
    return 'Woah, chill out!'   if sentence.is_shouting?
    return 'Sure.'              if sentence.is_a_question?
    return 'Whatever.'
  end

end

class String
  def is_a_question?
    self[-1] == '?'
  end

  def is_shouting?
    /[a-z]/.match(self).nil?
  end

  def is_silence?
    self !~ /[^[:space:]]/
  end
end

class NilClass
  def is_silence?
    true
  end
end
