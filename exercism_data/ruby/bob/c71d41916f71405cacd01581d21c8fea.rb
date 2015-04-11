class Bob
  def hey(phrase)
    return "Fine. Be that way!" if silent?(phrase)
    return "Woah, chill out!"   if yelling?(phrase)
    return "Sure."              if question?(phrase)
    "Whatever."
  end

  private

  def silent?(phrase)
    phrase.nil? || phrase.strip.empty?
  end

  def question?(phrase)
    phrase.end_with?("?")
  end

  def yelling?(phrase)
    phrase == phrase.upcase
  end
end
