class Robot
	attr_accessor :name

	def initialize
		reset
	end

	def reset
		@name = getRandomLetters + getRandomDigits.to_s
	end

	def getRandomLetters
		[*('a'..'z'), *('A'..'Z'), *('a'..'z'), *('A'..'Z')].sample(2).join
	end
	def getRandomDigits
		[*('0'..'9'), *('0'..'9'), *('0'..'9')].sample(3).join
	end
end
