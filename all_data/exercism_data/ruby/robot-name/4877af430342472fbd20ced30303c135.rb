class Robot
	attr_reader :name

	def initialize
		@name = new_name_generator
	end

	def new_name_generator
		alphabet = ('A'..'Z').to_a
		number = (1..9).to_a
		"" << alphabet.sample(2).join + number.sample(3).join
	end

	def reset
		initialize
	end
end
