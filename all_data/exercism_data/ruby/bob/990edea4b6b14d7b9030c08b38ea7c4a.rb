class Bob
  def hey(phrase)
    return 'Fine. Be that way!' if silence?(phrase)
    return 'Woah, chill out!' if shouting?(phrase)
    return 'Sure.' if question?(phrase)
    'Whatever.'
  end

  private
  def silence?(phrase)
    phrase.strip.empty?
  end

  def shouting?(phrase)
    phrase.upcase == phrase
  end

  def question?(phrase)
    phrase.end_with? '?'
  end
end
