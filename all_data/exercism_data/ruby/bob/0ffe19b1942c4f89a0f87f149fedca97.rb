class Bob

  def hey(phrase)
    if silent?(phrase)
      "Fine. Be that way!"
    elsif shouting?(phrase)
      "Woah, chill out!"
    elsif question?(phrase)
      "Sure."
    else
     "Whatever."
    end
  end

  private
  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with?("?")
  end

  def silent?(phrase)
    phrase.strip == ""
  end
end
