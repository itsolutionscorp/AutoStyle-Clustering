class Bob
 
  def hey(string)
    case 
    when silent(string)
      "Fine. Be that way!"
    when yelling(string)
      "Woah, chill out!"
    when questioning(string)
      "Sure."
    else 
      "Whatever."
    end	
  end

  def yelling(string)
    string == string.upcase
  end

  def questioning(string)
   string.end_with?('?')
  end

  def silent(string)
    string.strip.empty?
  end
end
