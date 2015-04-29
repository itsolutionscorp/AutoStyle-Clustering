class Robot
	def initialize
		@name = ""
	end
	def name
		2.times do
			@name << (65+rand(25)).chr
		end
		3.times do
			@name << (rand(10)).to_s
		end
		return @name
	end
	def reset
		@name = ""
	end
end
