class Bob

  def hey(input)
    return "Fine. Be that way." if silence?(input)
    return "Woah, chill out!" if shouting?(input)
    return "Sure." if question?(input)
    return "Whatever." if !silence?(input)
    statement
  end

  def shouting?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?('?')
  end

  def silence?(input)
    input.empty?
  end

end
