class Bob

  def hey(phrase)
    silence?(phrase) || shouting?(phrase) || question?(phrase) || statement
  end

  def question?(phrase)
    'Sure.' if phrase =~ /\?$/
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
