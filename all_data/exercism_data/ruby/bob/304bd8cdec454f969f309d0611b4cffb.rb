class Bob
	$default_response = "Whatever."
	$empty_response = "Fine. Be that way!"
	$excited_response = "Woah, chill out!"
	$question_response = "Sure."
	
	def hey(message_to_teenager)
		response = nil
		
		if is_empty_or_blank?(message_to_teenager)
			response = $empty_response
		elsif is_yelling?(message_to_teenager)
			response = $excited_response
		elsif is_questioning?(message_to_teenager)
			response = $question_response
		else
			response = $default_response
		end
		
		return response
	end
	
	private
	
	def is_empty_or_blank?(message)
		message.strip.empty?
	end
	
	def is_yelling?(message)
		all_caps_message = message.upcase
		return message == all_caps_message
	end
	
	def is_questioning?(message)
		message.end_with? "?"
	end

end
