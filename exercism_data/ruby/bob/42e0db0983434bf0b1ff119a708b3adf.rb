class Bob

	def hey(message)
		case
			when message.strip.empty? 
				response = 'Fine. Be that way!'
			when message.upcase == message
				response = "Woah, chill out!"
			when message.end_with?('?')
				response = 'Sure.'
			else
				response = 'Whatever.'
		end
	end

end
