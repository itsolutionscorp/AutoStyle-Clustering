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
    contain_upper_case?(phrase) &&
      not_contain_lower_case?(phrase)
  end

  UPPER_CASE_REGEX = /[A-Z]+/
  LOWER_CASE_REGEX = /[a-z]+/

  def contain_upper_case?(phrase)
    !!phrase.match(UPPER_CASE_REGEX)
  end

  def not_contain_lower_case?(phrase)
    !phrase.match(LOWER_CASE_REGEX)
  end
end
