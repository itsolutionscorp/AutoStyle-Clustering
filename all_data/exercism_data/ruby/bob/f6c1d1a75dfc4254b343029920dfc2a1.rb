class Bob < Object

  def hey(a)
    return "Fine. Be that way!" if a.strip.length==0
    return "Woah, chill out!" if a == a.upcase
    return "Sure." if a.end_with?("?")
    return "Whatever."
  end
end
