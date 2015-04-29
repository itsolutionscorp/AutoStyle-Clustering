class Bob
  def hey(message)
    
    if message.to_s.empty?
      return "Fine. Be that way."      
    elsif message.end_with?("?")
      return "Sure."
    elsif message == message.upcase
      return "Woah, chill out!"
    end
    
    "Whatever."
  end
end
