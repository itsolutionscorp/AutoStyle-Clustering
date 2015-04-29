class Bob

  def hey(input)
    return "Woah, chill out!" if shouting?(input)
    return "Sure." if input =~ /\?\Z/
    return "Fine. Be that way!" if input.strip.empty?
    "Whatever."
  end

  private

  def shouting?(input)
    input == input.upcase && input =~ /[A-Z]/
  end

end
