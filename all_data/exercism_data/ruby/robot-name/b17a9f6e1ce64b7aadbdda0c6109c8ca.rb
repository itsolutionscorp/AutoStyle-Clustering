class Robot

	attr_reader :name

	def name
		@name ||= generate_random_name
	end

	def generate_random_name
		# TWO LETTERS THREE NUMBERS
		('A'..'Z').to_a.sample(2).join('') + rand(100...999).to_s
	end

	def reset
		@name = nil
	end

end
