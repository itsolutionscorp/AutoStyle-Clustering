class Bob

  def hey(phrase)
    raise ArgumentError, 'Bob only likes strings.' unless phrase.respond_to?(:to_str)
    phrase = phrase.to_str
    silence?(phrase) || shouting?(phrase) || question?(phrase) || statement
  end

  def question?(phrase)
    'Sure.' if phrase.end_with?('?')
  end

  def shouting?(phrase)
    'Woah, chill out!' if phrase == phrase.upcase
  end

  def silence?(phrase)
    'Fine. Be that way!' if phrase.strip == ''
  end

  def statement
    'Whatever.'
  end

end
