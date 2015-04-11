class Robot

	attr_reader :name

	def initialize
		reset
	end

	def reset
		@name = generate_name
	end

private

	def generate_name
		random_letter + random_letter + random_number + random_number + random_number
	end

	def random_letter
		[*'A'..'Z'].sample
	end

	def random_number
		[*0..9].sample.to_s
	end

end
