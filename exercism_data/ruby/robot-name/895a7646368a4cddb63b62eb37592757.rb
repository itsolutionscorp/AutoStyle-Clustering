class Robot
	attr_accessor :name
	def initialize
		self.generate_name
	end
	def reset
		self.generate_name
	end
	def generate_name
		@name = (65 + rand(26)).chr + (65 + rand(26)).chr + rand(100...999).to_s
	end
end
