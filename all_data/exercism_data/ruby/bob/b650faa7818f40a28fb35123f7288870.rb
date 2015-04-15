class Bob
  
  def hey(string)
    
    case
    when silence?(string)
      'Fine. Be that way!'
    when yelling?(string)
      'Woah, chill out!'
    when question?(string)
      'Sure.'
    else
      'Whatever.'
    end

  end
  
  def silence?(string)
    string == nil || string == ''
  end
  
  def yelling?(string)
    string == string.upcase
  end
  
  def question?(string)
    string.end_with?("?")
  end
  
end
