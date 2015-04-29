class Bob
  def hey input
    return "Fine. Be that way!" if input.strip == ""
    return "Woah, chill out!" if input == input.upcase and input != input.downcase
    return "Sure." if input.end_with? "?"
    "Whatever."
  end
end
