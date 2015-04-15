class Bob
  
  def hey(message)
    if is_silent?(message)
      'Fine. Be that way!'
    elsif is_yelling?(message)
      'Woah, chill out!'
    elsif is_question?(message)
      'Sure.'
    else
      "Whatever."
    end
  end
 
  private 
  
  def is_yelling?(message)
     message.upcase == message
  end

  def is_question?(message)
    message.end_with?("?")
  end
  
  def is_silent?(message)
    message.nil? || message.strip.empty?
  end

end
