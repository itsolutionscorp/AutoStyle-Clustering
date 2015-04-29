class Robot
	def initialize
		@name = ""
		@name << ('A'..'Z').to_a.shuffle[0,2].join
		@name << (0..9).to_a.shuffle[0,3].join
	end

	def reset
		@name = ""
		@name << ('A'..'Z').to_a.shuffle[0,2].join
		@name << (0..9).to_a.shuffle[0,3].join
	end

	def name
		@name
	end

end
