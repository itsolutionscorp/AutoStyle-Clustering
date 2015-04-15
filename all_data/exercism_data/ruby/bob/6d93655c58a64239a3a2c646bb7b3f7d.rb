class Bob
  def hey(words)
    return "Fine. Be that way!" if words.to_s.strip.empty?
    return "Woah, chill out!" if words == words.upcase
    return "Sure." if words.end_with? "?"
    "Whatever."
  end
end
