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
    
  def silent?(str)
    str == nil || str.empty?
  end
  
  def yelling?(str)
    str == str.upcase
  end
  
  def question?(str)
    str.end_with?("?")
  end
end
