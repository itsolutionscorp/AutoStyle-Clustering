class Bob
  
  def hey(message)
	
	if message.nil? || message.empty?
		return "Fine. Be that way."
	
	elsif message.end_with?("?")
		return "Sure."
	
	elsif upcase?(message)
		return "Woah, chill out!"
	
	else
		return "Whatever."
	end
  end

   def upcase?(message)
   	message == message.upcase
   end
end
