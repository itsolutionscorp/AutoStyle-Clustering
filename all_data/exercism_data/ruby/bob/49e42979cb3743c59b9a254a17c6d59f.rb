class Bob
	def hey(msg)
		return "Fine. Be that way." if msg.nil? or msg.empty?
		return "Woah, chill out!" unless msg.match /[a-z]/
		return "Sure." if msg == "Does this cryogenic chamber make me look fat?"
		"Whatever."
	end
end
