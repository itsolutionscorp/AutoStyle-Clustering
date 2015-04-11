class Robot
	attr_accessor :name

	def initialize
	 	reset
	end

	def reset
		x = (0...2).map {  (('a'..'z').to_a + ('A'..'Z').to_a) [rand(52)] }.join
		y = rand 100...999
		self.name = "#{x}#{y}"
	end

end
