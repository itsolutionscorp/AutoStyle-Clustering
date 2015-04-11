class Bob

	def hey message	
		# note: .shout? must be before .simple_question?
		answer = case
			       when (silence? message) then 'Fine. Be that way!'
			       when (shout? message) then 'Woah, chill out!'
			       when (simple_question? message) then 'Sure.'
			       else "Whatever."
			       end

		answer
	end

end

def silence? message
	message.strip.empty?
end

def shout? message
	(message.upcase == message) && (has_word? message)
end

def simple_question? message
	message.end_with? '?'
end

def has_word? message
	!message.scan(/[a-zA-Z]+/).empty?
end
