class Bob
  def hey(message)
    if message.upcase == message
      return "Woah, chill out!" 
   
    elsif message.end_with?("?")
      return "Sure!"

    elsif message.end_with?(".")
      return "Whatever."
    end
  end
end
