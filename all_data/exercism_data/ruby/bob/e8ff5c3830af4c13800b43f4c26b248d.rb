class Bob

  def hey str
    return "Fine. Be that way!" if str.strip.empty?
    return "Woah, chill out!" if str.upcase == str && str.downcase != str
    return "Sure." if str[-1] == "?"
    "Whatever."
  end

end
