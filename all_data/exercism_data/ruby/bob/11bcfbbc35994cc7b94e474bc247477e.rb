class Bob
	def hey(msg)
		return "Fine. Be that way." if msg.nil? or msg.empty?
		return "Woah, chill out!" unless msg.match /[a-z]/
		return "Sure." if msg[-1] == "?"
		"Whatever."
	end
end
