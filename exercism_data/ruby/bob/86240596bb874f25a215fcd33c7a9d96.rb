class Bob

  def hey(text)
   
    if text.nil? || text.empty? 
      p 'Fine. Be that way!'
    
    elsif text == text.upcase
      p 'Woah, chill out!'
    
    elsif text.end_with?('?')
      p 'Sure.'
      
    else
      p 'Whatever.'
    end
    
  end
end
