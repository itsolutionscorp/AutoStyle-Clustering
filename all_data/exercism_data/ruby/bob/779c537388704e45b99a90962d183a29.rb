class Bob  
  def hey(input)
    case
    when silent?(input)
      "Fine. Be that way."
    when yelling?(input)
      "Woah, chill out!"
    when question?(input)
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
    
  def silent?(string)
    string == nil || string.empty?
  end
  
  def yelling?(string)
    string == string.upcase
  end
  
  def question?(string)
    string.end_with?("?")
  end
end
