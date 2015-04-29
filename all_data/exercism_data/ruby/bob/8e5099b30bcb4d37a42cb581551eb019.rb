class Bob
  RESPONSES = {
    :shouting  => "Woah, chill out!",
    :silence   => "Fine. Be that way.",
    :question  => "Sure.",
    :statement => "Whatever.",
  }

  def hey(statement)
    RESPONSES[interpret(statement)]
  end

  private

  def interpret(statement)
    return :silence if statement.nil? || statement == ""
    return :shouting if mostly_uppercase?(statement)
    return :question if question?(statement)
    return :statement
  end

  # If more than this percent of characters in your statement are upper case,
  # then you're yelling.
  SHOUTING_THRESHOLD = 0.5

  # Only counts letters, doesn't count numbers or punctuation either way.
  def mostly_uppercase?(statement)
    upper, lower = statement.scan(/[[:alpha:]]/).partition {|x| x.upcase == x}
    ratio = upper.length.to_f / lower.length.to_f
    ratio > SHOUTING_THRESHOLD
  end

  def question?(statement)
    statement[-1] == "?"
  end
end
