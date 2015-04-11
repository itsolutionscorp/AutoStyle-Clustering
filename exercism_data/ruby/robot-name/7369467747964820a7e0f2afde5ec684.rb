class Robot
	def initialize
		@name = generate_name
	end

	def name
		@name ||= generate_name
	end

	def reset
		@name = nil
	end

	def generate_name
		new_name = ""
		2.times { new_name += (('a'..'z').to_a + ('A'..'Z').to_a).sample }
		3.times { new_name += [*0..9].sample.to_s }
		
		new_name
	end
end
