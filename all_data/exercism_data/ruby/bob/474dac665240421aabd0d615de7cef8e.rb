class Bob
  def hey phrase
    return "Fine. Be that way!" if phrase.strip == ""
    return "Woah, chill out!" if phrase.upcase == phrase
    return "Sure." if phrase.ends_with? "?"
    "Whatever."
  end
end
