class Bob
	def hey(message)
		sentence = Sentence.new
		if sentence.no_response?(message)
			'Fine. Be that way.'
		elsif sentence.yelling?(message)
			'Woah, chill out!'
		elsif sentence.questioning?(message)
			'Sure.'
		else 
			'Whatever.'
		end
	end
end

class Sentence
	def no_response?(sentence)
		sentence.nil? || sentence.empty?
	end

	def yelling?(sentence)
		sentence == sentence.upcase
	end

	def questioning?(sentence)
		sentence.end_with?('?')
	end
end
