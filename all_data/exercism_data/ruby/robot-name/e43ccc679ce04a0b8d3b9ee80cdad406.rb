class Robot
	attr_accessor :name

	def initialize
		reset
	end

	def reset
		@name = getRandomLetters(2) + getRandomDigits(3).to_s
	end

	def getRandomLetters(count)
		count.times.map { [*('a'..'z'), *('A'..'Z')].sample }.join
	end
	def getRandomDigits(count)
		count.times.map { [*('0'..'9')].sample }.join
	end
end
