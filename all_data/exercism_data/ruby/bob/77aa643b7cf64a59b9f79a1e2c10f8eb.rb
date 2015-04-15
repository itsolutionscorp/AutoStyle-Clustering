class Bob
  def hey(phrase)
    return 'Fine. Be that way.' if silence? phrase
    return 'Woah, chill out!' if shouting? phrase
    return 'Sure.' if question? phrase

    'Whatever.'
  end

  protected

  def silence?(phrase)
    phrase.nil? or phrase.empty?
  end

  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with? '?'
  end
end
