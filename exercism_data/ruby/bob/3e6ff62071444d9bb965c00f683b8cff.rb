class Bob
  def hey(phrase)
    phrase = phrase.strip
    return 'Fine. Be that way!' if silent? phrase
    return 'Woah, chill out!' if shouting? phrase
    return 'Sure.' if question? phrase

    'Whatever.'
  end

  private
  def question?(phrase)
    phrase.end_with? '?'
  end

  def shouting?(phrase)
    phrase.upcase == phrase
  end

  def silent?(phrase)
    phrase.empty?
  end
end
