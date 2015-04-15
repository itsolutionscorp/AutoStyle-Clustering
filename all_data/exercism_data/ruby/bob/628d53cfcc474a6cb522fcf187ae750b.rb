class Bob
  RESPONSES = {
    :shouting  => "Woah, chill out!",
    :silence   => "Fine. Be that way.",
    :question  => "Sure.",
    :statement => "Whatever.",
  }

  def hey(statement)
    RESPONSES[interpret(statement.to_s)]
  end

  private

  def interpret(statement)
    return :silence  if empty?(statement)
    return :shouting if all_uppercase?(statement)
    return :question if ends_with_question_mark?(statement)
    return :statement
  end

  def empty?(statement)
    statement.nil? || statement.empty?
  end

  def all_uppercase?(statement)
    statement.upcase == statement
  end

  def ends_with_question_mark?(statement)
    statement.end_with?("?")
  end
end
