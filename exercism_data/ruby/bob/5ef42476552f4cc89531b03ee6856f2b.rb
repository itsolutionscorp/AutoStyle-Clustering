class Bob
  
  def hey(message)
    if yelling?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif silent_treatment?(message)
      "Fine. Be that way!"
    else 
    'Whatever.'
    end
  end
  
  def yelling?(message)
    message == message.upcase && message != message.downcase
  end
  
  def question?(message)
    message.end_with?("?")
  end
  
  def silent_treatment?(message)
    message.strip == ''
  end
  
end
