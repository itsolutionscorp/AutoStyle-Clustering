class Bob
  
  def hey(message)
     
    return "Fine. Be that way!" if (message.nil? or message.strip.empty?)
    return "Woah, chill out!" if (message.eql?(message.upcase))
    return "Sure." if (message.end_with?'?')
    return "Whatever."
    
  end
end
