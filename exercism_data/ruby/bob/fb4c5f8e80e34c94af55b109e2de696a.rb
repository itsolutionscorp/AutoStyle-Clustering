class Bob
  def hey(raw_phrase)
    phrase = Phrase.new(raw_phrase)

    return "Fine. Be that way!" if phrase.silent?
    return "Woah, chill out!"   if phrase.yelling?
    return "Sure."              if phrase.question?
    "Whatever."
  end
end

class Phrase
  def initialize(phrase)
    self.phrase = phrase
  end

  def silent?
    phrase.nil? || phrase.strip.empty?
  end

  def yelling?
    contains_upper_case? && !contains_lower_case?
  end

  def question?
    !!phrase.match(QUESTION_REGEX)
  end

  private

  attr_accessor :phrase

  UPPER_CASE_REGEX = /[A-Z]+/
  LOWER_CASE_REGEX = /[a-z]+/
  QUESTION_REGEX   = /\?$/

  def contains_lower_case?
    !!phrase.match(LOWER_CASE_REGEX)
  end

  def contains_upper_case?
    !!phrase.match(UPPER_CASE_REGEX)
  end
end
