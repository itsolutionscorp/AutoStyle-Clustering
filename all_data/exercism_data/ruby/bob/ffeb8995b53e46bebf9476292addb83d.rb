class Bob
  def hey(message)    
    if  message =~ /\A\s*\Z/
      'Fine. Be that way!'
    elsif message.upcase == message && message.downcase != message
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'      
    else
      'Whatever.'
    end  
  end
end
