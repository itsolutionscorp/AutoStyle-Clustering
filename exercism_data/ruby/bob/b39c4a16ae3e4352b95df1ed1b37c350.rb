class Bob
  def hey(phrase)
    phrase = phrase.strip.gsub(/\s/, "")

    if silence? phrase
      "Fine. Be that way!"
    elsif shouting? phrase
      "Woah, chill out!"
    elsif question? phrase
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?(phrase)
    phrase.empty?
  end

  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with? "?"
  end
end
