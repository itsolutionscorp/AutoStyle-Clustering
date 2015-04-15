class Bob
	def hey(words)
		return response Statement.new words
	end
	
	private
	def response statement
		case
		when statement.nothing?
			'Fine. Be that way!'
		when statement.yelling?
			'Woah, chill out!'
		when statement.question?
			'Sure.'
		else
			'Whatever.'
		end
	end
end

class Statement
	def initialize words
		@words = words
	end
	
	def question?
		return @words.match /[a-zA-Z]+\?/
	end
	
	def nothing?
		case @words
		when nil, "", /\s{2,}/
			true
		else
			false
		end
	end
	
	def yelling?
		return @words.eql? @words.upcase
	end
end
