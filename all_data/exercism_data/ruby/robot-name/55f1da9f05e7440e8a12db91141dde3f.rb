class Robot
	ALPHA = ('A'..'Z').to_a.freeze
	
	attr_reader :name 
	
	def initialize
		@name = randomname
	end	
	
	def reset
		@name = randomname
	end
	
	def randomname 
		ALPHA[rand(ALPHA.length)] <<  ALPHA[rand(ALPHA.length)] << rand(10).to_s << rand(10).to_s << rand(10).to_s
	end
end
