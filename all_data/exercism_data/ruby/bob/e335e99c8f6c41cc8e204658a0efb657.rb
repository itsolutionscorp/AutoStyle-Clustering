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
    true if statement.end_with?('?')
  end

  def shouting?(statement)
    true if statement.upcase == statement
  end

  def silence?(statement)
    true if statement.strip.empty?
  end
end
