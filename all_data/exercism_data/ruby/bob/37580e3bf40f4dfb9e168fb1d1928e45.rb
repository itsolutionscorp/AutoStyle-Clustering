class Bob
  def hey(phrase)
    return "Sure." if phrase.slice(-1) == '?'
    return "Fine, be that way." if phrase.length == 0
    return "Woah, chill out!" if phrase.upcase == phrase
    return "Whatever."
  end
end
