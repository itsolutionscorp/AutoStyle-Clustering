class Bob
  def hey(input)
    return "Fine. Be that way!" if input.rstrip  == ""
    return "Woah, chill out!" if input =~ /[a-zA-Z]/ && input == input.upcase
    return "Sure." if input.rstrip[-1] == "?"
    return "Whatever."
  end
end
