class Bob

	attr_reader :text

	def hey(text)
		@text = text
		return "Fine. Be that way!" if silence?
		return "Woah, chill out!" if shouting?
		return "Sure." if asking_a_question?
		return "Whatever."
	end

	private

	def silence?
		text.nil? || text.strip == ""
	end

	def shouting?
		text.upcase == text
	end

	def asking_a_question?
		text.match(/\?\z/)
	end

end
