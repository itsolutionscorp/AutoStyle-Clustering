class Bob
	def hey(words)
		response Statement.new words
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
		@words = String(words)
	end
	
	def question?
		@words.match /[a-zA-Z]+\?/
	end
	
	def nothing?
		@words.strip.empty?
	end
	
	def yelling?
		@words == @words.upcase
	end
end
