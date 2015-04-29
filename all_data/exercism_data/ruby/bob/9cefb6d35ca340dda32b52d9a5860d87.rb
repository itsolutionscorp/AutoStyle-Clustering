class Bob
	def hey(prompt)
		if prompt.nil? or prompt.empty?
			'Fine. Be that way.'
		elsif prompt.yelling?
			'Woah, chill out!'
		elsif prompt.question?
			'Sure.'
		else
			'Whatever.'
		end
	end
end

class String
	def yelling?
		self == upcase
	end

	def question?
		split('').last == '?'
	end
end
