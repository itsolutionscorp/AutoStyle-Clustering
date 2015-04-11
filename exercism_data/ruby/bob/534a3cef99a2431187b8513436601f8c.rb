class Bob

	def hey(message)
		case
		when !message.match(/\S/)
			"Fine. Be that way!"
		when message == message.upcase && message.match(/[A-Za-z]/)
			"Woah, chill out!"
		when message.end_with?("?")
			"Sure."
		else
			"Whatever."
		end
	end
end
