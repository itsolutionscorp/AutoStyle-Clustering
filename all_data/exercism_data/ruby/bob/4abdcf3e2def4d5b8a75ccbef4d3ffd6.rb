class Bob
  def hey(phrase)
    return "Woah, chill out!" if (phrase == phrase.upcase) and (phrase.index(/[a-zA-Z]/))
    return "Sure." if phrase[-1] == "?"
    return "Fine. Be that way!" if !phrase.index(/\S/)
    return "Whatever."
  end
end
