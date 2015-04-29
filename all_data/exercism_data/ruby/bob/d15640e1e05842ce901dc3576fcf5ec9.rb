class Bob
  def hey(phrase)
    if silence?(phrase)
      "Fine. Be that way."
    elsif yell?(phrase)
      "Woah, chill out!"
    elsif question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(phrase)
    String(phrase).empty?
  end

  def question?(phrase)
    phrase.end_with?("?")
  end

  def yell?(phrase)
    phrase == phrase.upcase
  end
end
