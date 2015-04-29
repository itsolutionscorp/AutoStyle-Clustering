class Bob
	def hey(prompt)
		return 'Fine. Be that way.' if prompt.nil? or prompt.blank?
		return 'Woah, chill out!' if prompt.yelling?
		return 'Sure.' if prompt.question?
		return 'Whatever.'
	end

end

class String
	def yelling?
		self == self.upcase
	end

	def question?
		self.split('').last == '?'
	end

	def blank?
		self == ''
	end
end
