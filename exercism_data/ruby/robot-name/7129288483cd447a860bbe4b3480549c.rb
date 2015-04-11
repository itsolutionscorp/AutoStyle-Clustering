class Robot
	attr_reader :name

	def initialize
		letters = (0...2).map { ('a'..'z').to_a[rand(26)] }.join
		numbers = rand.to_s[3...6]
		@name = letters + numbers
	end

	def reset
		initialize
	end
end
