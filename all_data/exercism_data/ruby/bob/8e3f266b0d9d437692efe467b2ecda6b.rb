class Bob
	def hey(chat)
		if chat == nil || chat.empty?
			self.annoyed_response
		elsif chat == chat.upcase
			self.surprised_response
		elsif chat.end_with? "?"
			self.question_response
		else
			self.generic_response
		end		
	end

	def annoyed_response
		"Fine. Be that way."
	end

	def surprised_response
		"Woah, chill out!"
	end

	def question_response
		"Sure."
	end

	def generic_response
		"Whatever."
	end

end
