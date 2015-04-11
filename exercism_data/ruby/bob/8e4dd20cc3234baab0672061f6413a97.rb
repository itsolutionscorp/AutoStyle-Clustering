class Bob 

  def hey (greeting)
    
    if greeting == nil or greeting.strip == ''
      "Fine. Be that way."	
    elsif greeting.end_with?('?')
      "Sure."
    elsif greeting.upcase! == nil
      "Woah, chill out!"
    else
      "Whatever."
    end
  
  end

end
