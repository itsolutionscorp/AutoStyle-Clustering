class Bob
    
  def hey(statement)
	
	case
    when no_statement?(statement)
      'Fine. Be that way!'
    when is_shouting?(statement)
      'Woah, chill out!'
    when is_question?(statement)
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
