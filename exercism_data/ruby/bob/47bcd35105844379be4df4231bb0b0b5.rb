class Bob

	def hey(message)

		if message.end_with?("?")
			"Sure."
		elsif message.scan(/[!]/) && message == message.upcase
			"Woah, chill out!"
		elsif message.empty?
			"Fine, Be that way!"
		else
			"Whatever."
		end
	end
end
