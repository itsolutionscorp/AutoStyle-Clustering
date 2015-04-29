class Robot
	attr_reader :name

	def initialize
		@name = generate_name
	end

	def generate_name
		(1..2).map { (65 + rand(26)).chr }.join + (1..3).map { (48 + rand(10)).chr }.join
	end

	def reset
		@name = generate_name
	end
end
