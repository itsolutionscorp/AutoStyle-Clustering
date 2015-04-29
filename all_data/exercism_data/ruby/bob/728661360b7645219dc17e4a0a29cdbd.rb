class Bob
  def hey(input)
    nothing(input) || shouting(input) || question(input) || other(input)
  end

  def nothing(input)
    "Fine. Be that way!" if input.strip == ""
  end

  def shouting(input)
    "Woah, chill out!" if input.match(/[A-Za-z]/) && input == input.upcase
  end

  def question(input)
    "Sure." if input.end_with?("?")
  end

  def other(input)
    "Whatever."
  end
end
