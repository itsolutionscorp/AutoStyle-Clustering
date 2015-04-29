class Bob

  def hey(statement)
    statement = statement.strip
    case 
    when silent?(statement)
      'Fine. Be that way!'  
    when shouting?(statement)
      'Woah, chill out!'
    when question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent?(statement)
    statement.empty?
  end

  def shouting?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    statement.end_with?("?")
  end

end
