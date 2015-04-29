class Bob
  def hey(input)
    return "Fine. Be that way!" if input.strip.empty?
    return "Woah, chill out!" if input.upcase == input
    return "Sure." if input[-1].eql?("?")
    "Whatever."
  end
end
