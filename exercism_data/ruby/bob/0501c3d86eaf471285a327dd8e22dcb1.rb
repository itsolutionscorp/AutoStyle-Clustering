class Bob
  def hey(message)
    message = message.to_s
    case 
    when giving_silent_treatment?(message)
      'Fine. Be that way!'
    when screaming?(message)
      'Woah, chill out!'
    when questioning?(message) 
      'Sure.'
    else
      "Whatever."
    end
  end

  private
  def giving_silent_treatment?(message)
    message.strip.empty?
  end

  def screaming?(message)
    message == message.upcase
  end

  def questioning?(message)
    message.end_with?('?')
  end
end
