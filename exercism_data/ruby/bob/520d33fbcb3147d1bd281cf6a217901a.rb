class Bob
	def hey(message)
		if $sentence.speechless?(message)
			'Fine. Be that way.'
		elsif $sentence.getting_yelled_at?(message)
			'Woah, chill out!'
		elsif $sentence.question_asked?(message)
			'Sure.'
		else 
			'Whatever.'
		end
	end
end

class Sentence

	def speechless?(sentence)
		sentence.nil? || sentence.empty?
	end

	def getting_yelled_at?(sentence)
		sentence == sentence.upcase
	end

	def question_asked?(sentence)
		sentence.end_with?('?')
	end
end

$sentence = Sentence.new
