class Bob

  def hey(word)
    return "Fine. Be that way!" if is_silence?(word)
    return "Woah, chill out!" if is_shouting?(word)
    return "Sure." if is_question?(word)
    "Whatever."
  end

  def is_silence?(word)
    word.to_s.empty?
  end

  def is_shouting?(word)
    word.upcase === word
  end

  def is_question?(word)
    word.end_with? '?'
  end
end
