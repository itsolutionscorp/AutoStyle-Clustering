class Robot
	LETTERS = ('A'..'Z').to_a
	NUMBERS = (0..9).to_a

	attr_reader:name
	def initialize
		@name = randName
	end

	def randName
		name = ''
		2.times {name += LETTERS[rand(25)]}
		3.times {name += NUMBERS[rand(9)].to_s}
		name
	end

	def reset
		initialize
	end
end
