class Bob
  
  def hey(trigger)
    if yelling? trigger
      'Woah, chill out!'
    elsif question? trigger
      'Sure.'
    elsif silence? trigger
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  
  private
   
  def yelling?(string)
    true if string =~ /[A-Z]{2,}/ && string == string.upcase
  end
  
  def question?(string)
    true if string.slice(-1,1) == '?'
  end
   
  def silence?(string)
    true if string.strip.empty?
  end
  
end
