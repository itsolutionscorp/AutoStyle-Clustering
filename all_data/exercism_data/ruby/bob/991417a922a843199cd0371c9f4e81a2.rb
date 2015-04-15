class Bob
	def hey(message)
		return "Fine. Be that way!" if empty? message
		return "Woah, chill out!" if shouting? message
		return "Sure." if message.end_with? "?"
		"Whatever."
	end


	private

	def empty?(message)
		/\A[[:space:]]*\z/.match message
	end

	def shouting?(message)
		return true if
			message.index(/[[:lower:]]/).eql? nil and
			/[[:upper:]]+/.match message
		false
	end
end
