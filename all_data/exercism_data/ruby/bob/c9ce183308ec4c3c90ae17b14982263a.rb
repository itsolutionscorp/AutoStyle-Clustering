class Bob
  def initialize

  end

  def hey(input)
    return "Sure." if input[-1] == "?"
    return "Fine, be that way." if input == ""
    return "Woah, chill out!" if input.upcase == input
    "Whatever."
  end
end
