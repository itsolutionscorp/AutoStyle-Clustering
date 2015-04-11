class Bob

  QUESTION = 'Sure.'
  YELLING = 'Woah, chill out!'
  SILENCE = 'Fine. Be that way!'
  OTHER = 'Whatever.'

  def hey (phrase)
    phrase.strip!
    return YELLING if yelling?(phrase)
    return QUESTION if question?(phrase)
    return SILENCE if silence?(phrase)
    return OTHER
  end

  def question?(phrase)
    !yelling?(phrase) && phrase.end_with?('?')
  end

  def yelling?(phrase)
    phrase.upcase == phrase && (phrase.end_with?('!') || !silence?(phrase))
  end

  def silence?(phrase)
    phrase.empty?
  end

end
