class Bob
  def hey statement 
    if silence? statement
      "Fine. Be that way!"
    elsif yelling? statement 
      "Whoa, chill out!"
    elsif question? statement
      "Sure."
    else
      "Whatever."
    end
  end

  def silence? statement 
    statement.strip.empty?
  end

  def yelling? statement
    statement.upcase == statement && statement.downcase != statement
  end

  def question? statement
    statement[ -1 ] == "?"
  end
end
