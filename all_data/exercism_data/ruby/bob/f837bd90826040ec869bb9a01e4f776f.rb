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

  private
  def empty?(statement)
    statement.strip.empty?
  end

  def shouting?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    statement.end_with? '?'
  end
end
