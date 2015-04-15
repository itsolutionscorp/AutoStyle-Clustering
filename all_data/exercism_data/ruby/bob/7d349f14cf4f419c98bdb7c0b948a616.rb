class Bob  
  def hey(message)
    case true  
    when yelling?(message)
      'Woah, chill out!'
    when question?(message)
      'Sure.'
    when silence?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  
  private 
  def yelling?(message)
    message == message.upcase && (message =~ /[A-Z]/) != nil
  end
  
  def question?(message)
    (message =~ /\?\Z/) != nil
  end
  
  def silence?(message)
    message.strip == ''
  end
end
