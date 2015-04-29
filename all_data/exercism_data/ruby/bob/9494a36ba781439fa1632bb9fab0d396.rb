class Bob

  def hey(statement)
    case statement
    when statement.strip.empty?
      "Fine. Be that way!"
    when statement == (statement.upcase)
      "Woah, chill out!"
    when statement.match /\?\z/   
      "Sure."
    else
      "Whatever."
    end
  end

end
