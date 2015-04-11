class Bob
  def hey(phrase)
    if phrase.match(/[A-z]/)
      return "Woah, chill out!" if phrase == phrase.upcase
      return "Sure." if phrase[-1] == "?"
      "Whatever."
    end
    return "Sure." if phrase[-1] == "?"
    return "Fine. Be that way!" if !(phrase.match(/\S/))
    "Whatever."
  end
end
# Looking forward to seeing how others made this code DRY.
