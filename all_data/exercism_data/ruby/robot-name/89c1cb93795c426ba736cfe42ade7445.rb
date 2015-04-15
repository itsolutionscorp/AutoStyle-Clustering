class Robot
	def initialize 
		@name = self.name
		letters = (0...2).map { ('a'..'z').to_a[rand(26)] }.join
		numbers = rand.to_s[3...6]
		@name = letters + numbers
	end

	def name 
		@name
	end

	def reset
		initialize
	end
end
