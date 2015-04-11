class Bob

  def hey string
    return 'Fine. Be that way!' if empty(string)
    return 'Woah, chill out!' if shouting(string)
    return 'Sure.' if question(string)
    return 'Whatever.'
  end

  private

  def empty string
    string.to_s.strip.empty?
  end

  def shouting string
    string == string.upcase
  end

  def question string
    string.end_with?('?')
  end

end
