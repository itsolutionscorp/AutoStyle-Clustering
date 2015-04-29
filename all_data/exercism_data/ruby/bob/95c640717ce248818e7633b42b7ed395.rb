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
    phrase.to_s.strip.empty?
  end

  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with?("?")
  end
end
