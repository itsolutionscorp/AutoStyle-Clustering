class Bob
	def hey(msg)
		msg.strip!
		return "Fine. Be that way!" if msg.empty?
		return "Woah, chill out!" if msg == msg.upcase
		return "Sure." if msg.end_with?("?")
		return "Whatever."
	end
end
