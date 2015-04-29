class Bob

  def hey(statement)
    statement = statement.strip
    case 
    when are_they_giving_the_silent_treatment?(statement)
      'Fine. Be that way!'  
    when are_they_shouting?(statement)
      'Woah, chill out!'
    when are_they_asking_a_question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def are_they_giving_the_silent_treatment?(statement)
    statement.empty?
  end

  def are_they_shouting?(statement)
    statement.upcase == statement
  end

  def are_they_asking_a_question?(statement)
    statement.end_with?("?")
  end

end
