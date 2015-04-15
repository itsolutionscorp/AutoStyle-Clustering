class Bob
  def hey(statement)
    if silence?(statement)
      'Fine. Be that way!'
    elsif shouting?(statement)
      'Woah, chill out!'
    elsif asking_a_question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asking_a_question?(statement)
    statement.end_with?('?')
  end

  def shouting?(statement)
    statement.upcase == statement
  end

  def silence?(statement)
    statement.strip.empty?
  end
end
