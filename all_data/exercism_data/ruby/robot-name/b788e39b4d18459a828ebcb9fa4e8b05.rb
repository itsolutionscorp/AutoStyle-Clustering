class Robot
	def initialize
		@name = gen_name
	end
	def gen_name
		"#{('a'..'z').to_a.shuffle[0]}#{('a'..'z').to_a.shuffle[0]}#{(0..9).to_a.shuffle[0]}#{(0..9).to_a.shuffle[0]}#{(0..9).to_a.shuffle[0]}"
	end
	def name
		@name
	end

	def reset
		@name = gen_name
	end
end
