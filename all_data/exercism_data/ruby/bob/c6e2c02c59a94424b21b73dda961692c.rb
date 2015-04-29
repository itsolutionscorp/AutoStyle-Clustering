class Bob
  WHATEVER = 'Whatever.'
  CHILL = 'Woah, chill out!'
  Q_AND_A = 'Sure.'
  SILENCE = 'Fine. Be that way!'

  def shouting?(text)
    #pluck out letters and worry about letters alone for shouting
    letters = text.scan(/[a-zA-Z]/)
    letters.any? && text == text.upcase
  end
  def question?(text)
    text.end_with?('?')
  end
  def silence?(text)
    text.strip.empty?
  end
  def hey(text)
    return SILENCE if silence?(text)
    return CHILL if shouting?(text)
    return Q_AND_A if question?(text)
    WHATEVER
  end
end
