class Bob
  def hey(statement)
    if statement.strip.empty?
      "Fine. Be that way!"
    elsif statement.match(/.*[A-Z]+.*/) && !statement.match(/.*[a-z].*/)
      "Woah, chill out!"
    elsif statement.end_with?("?")
      "Sure."
    else  
      "Whatever."
    end
  end
end
