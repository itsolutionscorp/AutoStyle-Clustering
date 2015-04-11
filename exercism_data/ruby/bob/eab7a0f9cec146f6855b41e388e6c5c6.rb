class Bob
  
  def hey statement
    if statement.gsub(/\s/,'').empty?
      "Fine. Be that way!"
    elsif caps? statement
      "Woah, chill out!"
    elsif statement.end_with? '?'
      "Sure."
    else
      "Whatever."
    end

  end

  def caps? statement
    if statement.gsub(/[^a-zA-Z]/,'') == statement.gsub(/[^a-zA-Z]/,'').upcase && !statement.gsub(/[^a-zA-Z]/,'').empty?
      true
    else  
      false
    end
  end

end
