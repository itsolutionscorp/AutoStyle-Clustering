class Bob
	def hey(message)
		return "Fine. Be that way!" if empty? message
		return "Woah, chill out!" if shouting? message
		return "Sure." if question? message
		"Whatever."
	end


	private

	def empty?(message)
		message.strip.empty?
	end

	def shouting?(message)
		message.upcase.eql? message and message.index /[[:alpha:]]/
	end

	def question?(message)
		return message.end_with? "?"
	end
end
