class Bob
  def hey(message)
    if message == "" || message.nil?
      return "Fine. Be that way."
    
    elsif message.upcase == message
      return "Woah, chill out!" 
   
    elsif message.end_with?("?")
      return "Sure."

    elsif message
      return "Whatever."

    end
  end
end
