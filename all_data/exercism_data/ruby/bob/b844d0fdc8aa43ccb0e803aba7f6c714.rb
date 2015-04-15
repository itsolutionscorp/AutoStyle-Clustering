class Bob
  def hey(phrase)
    if silence?(phrase)
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
  def silence?(phrase)
    phrase.strip.empty?
  end

  def shouting?(phrase)
    phrase.upcase == phrase
  end

  def question?(phrase)
    phrase.end_with?("?")
  end
end
