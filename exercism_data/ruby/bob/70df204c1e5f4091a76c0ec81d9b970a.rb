class Bob
  def hey(said)
    return "Fine. Be that way!" if said.strip == ""
    return "Woah, chill out!" if said == said.upcase && said.upcase != said.downcase
    return "Sure." if said.end_with? "?"
    "Whatever."
  end
end
