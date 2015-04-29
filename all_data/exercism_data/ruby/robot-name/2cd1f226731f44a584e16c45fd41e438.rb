class Robot
	def initialize
		@name = generate_name
	end

	def name
		return @name
	end

	def generate_name
		(0...2).map { (65 + rand(26)).chr }.join + (Random.new.rand(100..1000)).to_s
	end

	def reset
		@name = generate_name
	end
end
