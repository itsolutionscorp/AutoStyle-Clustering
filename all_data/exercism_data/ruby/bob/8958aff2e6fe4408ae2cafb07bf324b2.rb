class Bob
  def hey(sentence)
    return 'Fine. Be that way!' if is_silence? sentence
    return 'Woah, chill out!'   if is_shouting? sentence
    return 'Sure.'              if is_a_question? sentence
    return 'Whatever.'
  end

  protected

  def is_a_question?(sentence)
    sentence[-1] == '?'
  end

  def is_shouting?(sentence)
    /[a-z]/.match(sentence).nil?
  end

  def is_silence?(sentence)
    sentence.nil? or sentence !~ /[^[:space:]]/
  end
end
