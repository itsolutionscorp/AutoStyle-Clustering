class Bob
  def hey str    
    if silence?(str)
 	  'Fine. Be that way!'
    elsif shouted_at?(str) 
      'Woah, chill out!'
    elsif questioned?(str)
      'Sure.'      
    else
      'Whatever.'
    end
  end

  def shouted_at?(str)
  	if str == str.upcase
  	  if str == str.downcase
  		  return false
  	  end
  	  return true
  	end 	  
  end

  def questioned?(str)
		str.end_with? "?"    
  end 
	
  def silence?(str)
		str.empty? || str.strip.empty?
  end

end
