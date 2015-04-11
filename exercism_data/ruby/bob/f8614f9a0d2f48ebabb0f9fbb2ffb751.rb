class Bob
	def hey(chat)
		if chat.lstrip == ''
			"Fine. Be that way!"
		elsif chat.upcase == chat && chat.length > 2 && chat[-1] != '3'  
			"Woah, chill out!"
		elsif chat[-1] == "?" 
			"Sure."
		else
			"Whatever."
		end
	end	
end
