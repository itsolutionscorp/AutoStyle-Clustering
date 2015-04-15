class Bob
  
  # Respond to a message with a surly String
  def hey(message)
    if nothing?(message)
      'Fine. Be that way!'
    elsif yelling?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      "Whatever."
    end
  end
  
  protected
  
  # Check if the message is nil or only whitespace
  def nothing?(message)
    message.nil? || message.strip.empty?
  end
  
  # Check if the message ends with a question mark
  def question?(message)
    message =~ /\?\Z/
  end
  
  # Check if the message is all-caps and contains some text
  def yelling?(message)
    message == message.upcase && message =~ /[A-Z]/
  end
  
end
