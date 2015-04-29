class Bob

  def hey(phrase)
    phrase.strip!
    return "Fine. Be that way!" if is_silence?(phrase)
    return "Whoa, chill out!" if is_shout?(phrase)
    return "Sure." if is_question?(phrase)
    "Whatever."
  end

  private

  def is_silence?(phrase)
    phrase == ''
  end

  def is_shout?(phrase)
    phrase.upcase == phrase && only_alpha?(phrase)
  end

  def is_question?(phrase)
    phrase[phrase.length-1] == "?"
  end

  def only_alpha?(phrase)
    /\p{L}+/.match(phrase)
  end
end
