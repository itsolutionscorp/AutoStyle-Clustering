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
    return :silence  if empty?(statement)
    return :shouting if all_uppercase?(statement)
    return :question if ends_with_question_mark?(statement)
    return :statement
  end

  def empty?(statement)
    statement.nil? || statement == ""
  end

  def all_uppercase?(statement)
    statement.scan(/[[:alpha:]]/).all? {|x| x.upcase == x}
  end

  def ends_with_question_mark?(statement)
    statement.end_with?("?")
  end
end
