class Bob
  def hey(words)
    return "Fine. Be that way!" if words.to_s.gsub(/[[:space:]]/, '').empty?
    return "Woah, chill out!" if words == words.upcase
    return "Sure." if words[-1] == "?"
    "Whatever."
  end
end
