class Bob

  def hey speech
    return 'Fine. Be that way!' if silence?(speech)
    return 'Woah, chill out!' if shouting?(speech)
    return 'Sure.' if question?(speech)
    'Whatever.'
  end

  def silence?(speech)
    speech.nil? or speech.strip.empty?
  end

  def shouting?(speech)
    speech == speech.upcase
  end

  def question?(speech)
    speech[-1] == '?'
  end

end
