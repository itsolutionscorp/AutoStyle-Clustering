class Bob
  def hey statement
    respond_with statement
  end

  def respond_with statement
    return "Whatever." unless statement.is_a? String

    if silence? statement 
      "Fine. Be that way!"
    elsif yell? statement 
      "Woah, chill out!"
    elsif question? statement
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence? statement
    statement.strip.empty?
  end

  def yelling? statement
    statement == statement.upcase && statement != statement.downcase
  end

  def question? statement
    statement.end_with? "?"
  end

end
