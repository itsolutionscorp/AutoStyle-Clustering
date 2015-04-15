class Bob

  def hey(statement)
    if statement.strip.empty?
      return "Fine. Be that way!"
    elsif statement == (statement.upcase)
      return "Woah, chill out!"
    elsif statement.match /\?\z/   
      return "Sure."
    else
      return "Whatever."
    end
  end

end
