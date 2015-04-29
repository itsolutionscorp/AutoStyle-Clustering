class Bob
	def hey(convo)
		if convo.nil? || convo.empty?
 			return "Fine. Be that way."
		elsif convo.end_with?("?")
 			return "Sure."
		elsif convo == convo.upcase 
 			return "Woah, chill out!"
		else
  			return "Whatever."
		end
	end
end
