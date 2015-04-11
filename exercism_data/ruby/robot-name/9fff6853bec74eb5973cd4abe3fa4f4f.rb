#this generates random names for robots using ASCII codes and the chr method
#reset gets rid of a name and initializes a new one
class Robot
attr_accessor :name
	def initialize
		@name = ""
			2.times do 
				@name << (65 + rand(26)).chr
			end
			3.times do
				@name << (48 + rand(10)).chr
			end
	end

	def reset
		@name = ""
			2.times do 
				@name << (65 + rand(26)).chr
			end
			3.times do
				@name << (48 + rand(10)).chr
			end
	end
end
