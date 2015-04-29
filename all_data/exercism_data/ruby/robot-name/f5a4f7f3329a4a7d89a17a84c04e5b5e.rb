class Robot
	def generate_name
		name = ([*('A'..'Z')].sample(2)).join + ([*(0..9)].sample(3)).join
		return name
	end

	def initialize
		@name = generate_name
		def reset
			@name = generate_name
		end

		def name
		@name
		end
	end
end
