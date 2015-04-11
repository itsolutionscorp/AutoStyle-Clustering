class Bob
    
  def hey(message)
    
    case message
    
    when  /\n/  then    'Whatever.' 
        
    when /^\s*$/ then   'Fine. Be that way!'
      
    when  /^[0-9A-Z\s!?,%^*@#\$(]+$/  then   'Woah, chill out!'
      
    when  /[\?]$/  then   'Sure.'
             
    else   'Whatever.' 
      
    end    
    
  end
  
end
