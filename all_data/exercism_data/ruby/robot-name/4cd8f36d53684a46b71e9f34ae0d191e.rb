class Robot
	attr_accessor :name

	def initialize
		@name = (0..2).map{ (65 + rand(26)).chr }.join + (0..3).map{ rand(9).to_s }.join
	end
	
	def reset
		@name = (0..2).map{ (65 + rand(26)).chr }.join + (0..3).map{ rand(9).to_s }.join
	end

end
