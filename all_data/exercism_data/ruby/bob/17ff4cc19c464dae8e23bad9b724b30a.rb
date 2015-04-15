class Bob
  def hey(statement)
    case type_of_statment(statement)
    when "empty" then "Fine. Be that way!"
    when "shouting" then "Woah, chill out!"
    when "question" then "Sure."
    else "Whatever."
    end
  end
  
  def type_of_statment(statement)
    case statement
    when statement.strip.empty? then "empty"
    when (statement.match(/.*[A-Z]+.*/) && !statement.match(/.*[a-z].*/)) then "shouting"
    when statement.end_with?("?") then "question"
    end
  end
end
