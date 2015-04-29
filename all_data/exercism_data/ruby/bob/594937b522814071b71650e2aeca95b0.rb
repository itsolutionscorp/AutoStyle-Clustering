class Bob
	def hey(message)
		message.strip!
		return "Fine. Be that way!" if message.empty?
		return "Woah, chill out!" if message == msg.upcase
		return "Sure." if message.end_with?("?")
		return "Whatever."
	end
end
