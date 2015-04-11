class Bob
  def initialize
  end

  def hey(communication)
    @communication = communication
    return "Fine. Be that way!" if non_verbal_communication?
    return "Woah, chill out!" if yelled_at?
    return "Sure." if asked_a_question?
    "Whatever." #Otherwise
  end

  def yelled_at?
    statement_contains_words? && statement_is_in_all_caps?
  end

  def asked_a_question?
    @communication.end_with?("?")
  end

  def non_verbal_communication?
    @communication.strip.empty?
  end

  def statement_contains_words?
    @communication.scan(/[A-z]/).count > 0
  end

  def statement_is_in_all_caps?
    @communication.upcase == @communication
  end
end
