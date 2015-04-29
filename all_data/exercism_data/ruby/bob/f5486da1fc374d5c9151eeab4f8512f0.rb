class Bob
    
  def hey(statement)
	
    if no_statement?(statement)
      'Fine. Be that way!'
    elsif is_shouting?(statement)
      'Woah, chill out!'
    elsif is_question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def no_statement?(statement)
    statement.to_s.strip == ""
  end

  def is_shouting?(statement)
    statement == statement.upcase
  end

  def is_question?(statement)
    statement.end_with?('?')
  end
end
