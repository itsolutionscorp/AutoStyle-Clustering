class Bob
	def hey(msg)
		return "Fine. Be that way!" if (msg.nil? or msg.empty?)
		return "Woah, chill out!"   if (msg =~ /^[A-Z\d\W]+[!\?]*$/)
		return "Sure." 			    if (msg.end_with? "?")
		return "Whatever."
	end
end
