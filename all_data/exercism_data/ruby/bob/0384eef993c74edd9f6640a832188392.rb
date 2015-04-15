class Bob
  def hey(phrase)
    if silence?(phrase)
      'Fine. Be that way!'
    elsif shouting?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else      
      'Whatever.'
    end
  end
  
  private
  
  def silence?(string)
    string.lstrip == ""
  end
  
  def has_alpha?(string)
    string =~ /[A-Za-z]+/
  end
  
  def shouting?(string)
    (string == string.upcase) && has_alpha?(string)
  end
  
  def question?(string)
    string.end_with?('?')
  end
  
end
