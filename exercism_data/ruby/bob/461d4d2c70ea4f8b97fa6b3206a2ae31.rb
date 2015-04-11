class Bob
  
  def hey statement
    if silent?(statement)
      "Fine. Be that way!"
    elsif yelling?(statement)
      "Woah, chill out!"
    elsif question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def silent? statement
    statement.strip.empty?
  end

  def yelling? statement
    statement = scrub(statement)
    statement == statement.upcase && !statement.empty?
  end

  def question? statement
    statement.end_with? '?'
  end

  def scrub statement
    statement.gsub(/[^a-zA-Z]/,'') 
  end

end
