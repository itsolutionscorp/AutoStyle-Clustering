class Bob
	def hey(msg)
		if msg[-1] == "?"
			"Sure."
		elsif msg == msg.upcase
			"Woah, chill out!"
		elsif msg.chomp.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end	
	end
end
