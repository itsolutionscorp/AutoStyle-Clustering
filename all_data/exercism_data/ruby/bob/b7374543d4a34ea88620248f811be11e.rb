class Bob

  QUESTION = 'Sure.'
  YELLING = 'Woah, chill out!'
  SILENCE = 'Fine. Be that way!'
  OTHER = 'Whatever.'

  def hey (phrase)
    phrase.strip!
    if yelling?(phrase)
      YELLING
    elsif question?(phrase)
      QUESTION
    elsif silence?(phrase)
      SILENCE
    else
      OTHER
    end
  end

  def question?(phrase)
    !yelling?(phrase) && phrase.end_with?('?')
  end

  def yelling?(phrase)
    phrase.upcase == phrase && (phrase.end_with?('!') || !silence?(phrase))
    #/^[A-Z]{2,}/.match(@phrase) ||
    #/^[A-Z]+\s?[A-Z]{2,}/.match(@phrase) || /[A-Z]+\!$/.match(@phrase)
  end

  def silence?(phrase)
    phrase.empty?
  end

end
