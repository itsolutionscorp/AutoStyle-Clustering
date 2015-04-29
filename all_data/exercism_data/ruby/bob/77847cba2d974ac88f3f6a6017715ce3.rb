class Bob

  def hey(phrase)
    if is_silent?(phrase)
      "Fine. Be that way!"
    elsif is_shouting?(phrase)
      "Woah, chill out!"
    elsif is_question?(phrase)
      "Sure."
    else
     "Whatever."
    end
  end

private

  def is_shouting?(phrase)
    phrase == phrase.upcase
  end

  def is_question?(phrase)
    phrase.end_with?("?")
  end

  def is_silent?(phrase)
    phrase.strip == ""
  end

end
