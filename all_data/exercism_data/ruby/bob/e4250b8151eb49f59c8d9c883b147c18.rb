class Bob
  def hey(statement)
    if silent_treatment?(statement)
      'Fine. Be that way.'
    elsif shouting?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent_treatment?(statement)
    statement.empty?
  end

  def shouting?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    statement.end_with?('?')
  end
end
