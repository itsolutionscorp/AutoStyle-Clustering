class String
	def scream?
		self == self.upcase unless self.empty?
	end
	
	def question?
		self.end_with? '?'
	end
end

class Bob
	def hey(sentence)
		if sentence.scream?
			'Woah, chill out!'
		elsif sentence.question?
			'Sure'
		elsif sentence.empty?
			'Fine. Be that way!'
    else
      'Whatever.'
		end
	end
end
