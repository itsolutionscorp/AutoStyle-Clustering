class Robot
	attr_reader :name
	def initialize
		assign_name
	end
	def assign_name
		@name = (0..1).map { (65 + rand(26)).chr }.join + (0..2).map { rand(9) }.join
	end
	alias :reset :assign_name
end
