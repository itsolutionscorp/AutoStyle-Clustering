class Bob

  def hey(input)
    silence?(input) || shouting?(input) || question?(input) || statement
  end

  def shouting?(input)
    return "Woah, chill out!" if input == input.upcase
  end

  def question?(input)
    return "Sure." if input.end_with?('?')
  end

  def silence?(input)
    return "Fine. Be that way." if input.empty?
  end

  def statement
    "Whatever."
  end


end
