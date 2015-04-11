class Bob
  def hey(string)
    return "Fine. Be that way." if silence?(string)
    return "Woah, chill out!" if forceful_question?(string)
    return "Sure." if question?(string)
    return "Woah, chill out!" if shouting?(string)
    return "Whatever." if forceful?(string)

    "Whatever."
  end

  private

  def shouting?(string)
    string =~ /^([^a-z])+$/
  end

  def forceful?(string)
    string.end_with?('!')
  end

  def question?(string)
    string.end_with?('?')
  end

  def forceful_question?(string)
    string =~ /^([^a-z])+\?$/
  end

  def silence?(string)
    string.to_s.empty?
  end
end
