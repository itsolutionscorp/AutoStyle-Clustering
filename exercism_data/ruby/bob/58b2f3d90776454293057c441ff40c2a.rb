class Bob
  def hey(statement)
    if empty? statement
      'Fine. Be that way!'
    elsif shouting? statement
      'Woah, chill out!'
    elsif question? statement
      'Sure.'
    else
      'Whatever.'
    end
  end

  def empty?(statement)
    statement.strip.empty?
  end

  def shouting?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    last_character(statement) == '?'
  end

  def last_character(statement)
    statement[-1,1]
  end
end
