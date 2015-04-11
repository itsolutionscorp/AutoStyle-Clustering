class Bob

  def hey(text)
   
    if text.nil? || text.empty? 
      'Fine. Be that way.'
    
    elsif text == text.upcase
      'Woah, chill out!'
    
    elsif text.end_with?('?')
      'Sure.'
      
    else
      'Whatever.'
    end
    
  end
end
