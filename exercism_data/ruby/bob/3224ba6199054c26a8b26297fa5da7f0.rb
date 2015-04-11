class Bob
  def hey(statement)
    
    case 
    when statement.strip.empty? then "Fine. Be that way!"
    when (statement.match(/.*[A-Z]+.*/) && !statement.match(/.*[a-z].*/)) then "Woah, chill out!"
    when statement.end_with?("?") then "Sure."
    else "Whatever."
    end

  end
end
