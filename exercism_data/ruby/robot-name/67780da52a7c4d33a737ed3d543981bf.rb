class Robot
	def initialize
		alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		numeric = "0123456789"
		@name = alpha[rand(alpha.length)] +  alpha[rand(alpha.length)]
		0.upto(2)  { @name << numeric[rand(numeric.length)] }
	end

	def name
		@name
	end
end

puts Robot.new.name
