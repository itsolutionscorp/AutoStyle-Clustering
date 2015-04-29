class Bob
  def hey statement
    respond_with statement
  end

  def respond_with statement
    return "Whatever." unless statement.is_a? String

    if statement.strip.empty? 
      "Fine. Be that way!"
    elsif statement.upcase == statement and statement =~ /[A-Z]+/  
      "Woah, chill out!"
    elsif statement.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
