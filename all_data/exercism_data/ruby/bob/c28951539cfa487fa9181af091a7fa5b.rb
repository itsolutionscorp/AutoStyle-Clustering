class Bob

  def hey(greeting)
    return 'Fine. Be that way!' if silence?(greeting)
    return 'Woah, chill out!' if shouting?(greeting)
    return 'Sure.' if question?(greeting)
    'Whatever.'
  end

  private

  def silence?(text)
    text.nil? || text.strip.empty?
  end

  def shouting?(text)
    text == text.upcase
  end

  def question?(text)
    text.end_with?('?')
  end

end
