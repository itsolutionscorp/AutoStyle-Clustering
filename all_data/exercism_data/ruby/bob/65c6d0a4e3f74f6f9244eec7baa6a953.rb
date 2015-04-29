class Bob
  def hey(phrase)
    if silence?(phrase)
      "Fine. Be that way!"
    elsif includes_shouting?(phrase)
      "Woah, chill out!"
    elsif includes_non_shouting_question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?(phrase)
    phrase.to_s.strip.empty?
  end

  def includes_shouting?(phrase)
    phrase == phrase.upcase
  end

  def includes_non_shouting_question?(phrase)
    phrase.end_with?("?")
  end
end
