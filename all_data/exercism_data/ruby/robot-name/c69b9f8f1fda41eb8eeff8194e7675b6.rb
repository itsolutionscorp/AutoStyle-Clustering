class Robot
	def initialize
		@name = create_name
	end
	def name
		@name
	end
	def create_name
		name = ""
		2.times do
			name << add_rand_char
		end
		3.times do
			name << add_rand_int_as_str
		end
		name
	end
	def add_rand_char
		("A".."Z").to_a.sample
	end
	def add_rand_int_as_str
		(0..9).to_a.sample.to_s
	end
	def reset
		@name = create_name
	end

end
