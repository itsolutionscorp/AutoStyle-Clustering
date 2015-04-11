class Robot
	attr_accessor :name
	def initialize
		@name = (65 + rand(26)).chr + (65 + rand(26)).chr + rand(100...999).to_s
	end
	def reset
		@name = (65 + rand(26)).chr + (65 + rand(26)).chr + rand(100...999).to_s
	end
end
