class Bob

  def hey(statement)
     case 
      when statement == ("")
      "Fine. Be that way!"
      when statement == statement.upcase
      "Woah, chill out!"
      when statement.end_with?("?")
      "Sure."
      else
      "Whatever."
    end
  end
end
