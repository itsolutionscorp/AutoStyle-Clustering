class Bob

  def hey(string)
    return 'Fine. Be that way.' if blank?(string)
    return 'Sure.' if question?(string)
    return 'Woah, chill out!' if shouting?(string)
    'Whatever.'
  end

  private

  def blank?(string)
    string.nil? || string.empty?
  end

  def question?(string)
    string.match(/\?$/)
  end

  def shouting?(string)
    string.match(/^[^a-z]+$/) && string.match(/[A-Z]+/)
  end

end
