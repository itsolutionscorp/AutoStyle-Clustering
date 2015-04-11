class Bob
	def hey(chat)
		if chat == nil || chat.empty?
			"Fine. Be that way."
		elsif chat == chat.upcase
			"Woah, chill out!"
		elsif chat.end_with? "?"
			"Sure."
		else
			"Whatever."
		end		
	end
end
