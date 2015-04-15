class Bob

	def hey message
		if silence? message
			return 'Fine. Be that way.'
		end	
		if shouting? message
			return 'Woah, chill out!' 
		end
		if question? message
			return 'Sure.'
		end
		'Whatever.'
	end

	def shouting? message
		message == message.upcase
	end	
	def question? message
		message[-1,1] == '?'
	end	
	def silence? message
		message == nil || message == ''
	end	
	 
	private :question?,:shouting?,:silence?
end	
