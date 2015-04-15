class Bob
	def hey(prompt)

		prompt = Prompt.new prompt.to_s

		if prompt.boring?
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
		end_with? '?'
	end

	def boring?
		empty?
	end
end
