class Robot
	def initialize()
		@name = ""
		(2.times{@name << ('a'..'z').to_a.sample}) + (3.times{@name << rand(0..9).to_s})
	end

	def name
		return @name
	end

	def reset
		@name = ""
		(2.times{@name << ('a'..'z').to_a.sample}) + (3.times{@name << rand(0..9).to_s})
	end
end
