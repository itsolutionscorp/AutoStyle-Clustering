class Bob
  def hey(phrase)
    if nil_or_empty?(phrase)
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
  def nil_or_empty?(phrase)
    phrase.nil? || phrase.strip.empty?
  end

  def includes_shouting?(phrase)
    phrase == phrase.upcase
  end

  def includes_non_shouting_question?(phrase)
    phrase.end_with?("?")
  end
end
