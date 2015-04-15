class Robot
	def initialize
		@name = rand(65...90).chr + rand(65...90).chr + sprintf('%03d', rand(0..999))
	end
	def name
		@name
	end
	def reset
		@name = rand(65...90).chr + rand(65...90).chr + sprintf('%03d', rand(0..999))
	end
end
