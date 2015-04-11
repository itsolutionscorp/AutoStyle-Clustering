class Bob

  def hey(sentence)
    return 'Fine. Be that way!' if blank?(sentence)
    return 'Woah, chill out!' if yelling?(sentence)
    return 'Sure.' if asking?(sentence)
    'Whatever.'
  end

  def blank?(sentence)
    /^\s*$/.match sentence
  end

  def yelling?(sentence)
    sentence.upcase == sentence
  end

  def asking?(sentence)
    sentence.end_with? '?'
  end


end
