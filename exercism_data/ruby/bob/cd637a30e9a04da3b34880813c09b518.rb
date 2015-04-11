class Bob
	def hey(prompt)
		return 'Fine. Be that way.' if prompt.nil? or prompt.is_blank
		return 'Woah, chill out!' if prompt.is_yelling
		return 'Sure.' if prompt.is_a_question
		return 'Whatever.'
	end

end

class String
	def is_yelling
		self == self.upcase
	end

	def is_a_question
		self.split('').last == '?'
	end

	def is_blank
		true if self == ''
	end
end
