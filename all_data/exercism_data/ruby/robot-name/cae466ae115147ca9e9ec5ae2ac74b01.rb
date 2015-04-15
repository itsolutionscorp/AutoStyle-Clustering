class Robot
	attr_reader :name
	def initialize
		@name = ""
		@name << ('a'..'z').to_a.push(('A'..'Z').to_a).flatten.shuffle[0]
		@name << ('a'..'z').to_a.push(('A'..'Z').to_a).flatten.shuffle[0]
		@name << (0..9).to_a.shuffle[0].to_s
		@name << (0..9).to_a.shuffle[0].to_s
		@name << (0..9).to_a.shuffle[0].to_s
	end
	def reset
		initialize
	end

end
