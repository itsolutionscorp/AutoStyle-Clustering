class Bob
	def hey(prompt)
		return 'Fine. Be that way.' if prompt.nil?

		prompt = Prompt.new prompt

		if prompt.empty?
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

class Prompt < String

	def yelling?
		self == upcase
	end

	def question?
		ends_with? '?'
	end
end
