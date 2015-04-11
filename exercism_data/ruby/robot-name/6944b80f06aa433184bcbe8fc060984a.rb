class Robot
	
	attr_reader :name

	def initialize
	  reset
	end

	def reset
	  @name = (('A'..'Z').to_a.sample(2) + (0..9).to_a.sample(3)).join
	end
end
