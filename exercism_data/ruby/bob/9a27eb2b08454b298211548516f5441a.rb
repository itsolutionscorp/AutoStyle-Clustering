class Bob
	def hey(message)
		return "Fine. Be that way!" if message == nil or message == ''
		return "Woah, chill out!" if yelling?(message)
		return "Sure." if question?(message)
		"Whatever."
	end

	def yelling?(message)
		message !~ /[a-z]/
	end

	def question?(message)
		message =~ /\?\Z/
	end
end
