class Bob
  def hey(phrase)
    return 'Fine. Be that way!' if silence? phrase
    return 'Woah, chill out!' if shouting? phrase
    return 'Sure.' if question? phrase
    'Whatever.'
  end

  def silence?(phrase)
    phrase.strip.empty?
  end

  def shouting?(phrase)
    stripped = phrase.gsub(/[^a-zA-Z]/, '')
    !stripped.empty? && stripped.upcase == stripped
  end

  def question?(phrase)
    phrase.end_with? '?'
  end
end
