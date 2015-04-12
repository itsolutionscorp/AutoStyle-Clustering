class Bob
  
  def hey(message)
   
    if silent?(message)
      'Fine. Be that way!'
    elsif yelling?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      "Whatever."
    end
  end
 
  private 
  
  def yelling?(message)
     message.upcase == message
  end

  def question?(message)
    message.end_with?("?")
  end
  
  def silent?(message)
    message.nil? || message.empty? || message.gsub(' ', '').empty?
  end

end