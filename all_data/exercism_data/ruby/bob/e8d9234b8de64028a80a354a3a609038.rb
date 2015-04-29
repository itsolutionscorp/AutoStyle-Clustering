class Bob
	def hey(msg)
		msg.strip!
		"Fine. Be that way!" if msg.empty?
		"Woah, chill out!" if msg == msg.upcase
		"Sure." if msg.end_with?("?")
		"Whatever."
	end
end
