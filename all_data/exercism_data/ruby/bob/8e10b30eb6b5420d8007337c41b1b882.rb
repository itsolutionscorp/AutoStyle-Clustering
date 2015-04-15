class Bob

  def hey(sentence)
    return "Fine. Be that way!" if silence?(sentence)
    return "Woah, chill out!" if shouting?(sentence)
    return "Sure." if question?(sentence)
    "Whatever."
  end

  def silence?(sentence)
    sentence.strip == ""
  end

  def has_words?(sentence)
    sentence.match(/[a-zA-Z]/)
  end

  def question?(sentence)
    sentence.end_with? "?"
  end

  def shouting?(sentence)
    has_words?(sentence) && sentence.upcase == sentence
  end

end
