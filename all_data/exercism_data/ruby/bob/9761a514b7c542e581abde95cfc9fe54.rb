class Bob
  
  def hey(input)
    if yelling? input
      'Woah, chill out!'
    elsif question? input
      'Sure.'
    elsif silence? input
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  
  private
   
  def yelling?(string)
    string =~ /[A-Z]{2,}/ && string == string.upcase
  end
  
  def question?(string)
    string.end_with? '?'
  end
   
  def silence?(string)
    string.strip.empty?
  end
  
end
