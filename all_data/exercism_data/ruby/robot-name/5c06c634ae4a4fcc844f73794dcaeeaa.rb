class Robot
	attr_accessor :name
	@@blacklist = {}

	def initialize
		self.reset
	end

	def reset
		@name = ""
		loop do
			@name = "".concat(Random.rand(26) + 65).concat(Random.rand(26) + 65) + Random.rand(10).to_s + Random.rand(10).to_s + Random.rand(10).to_s
			break unless @@blacklist[@name] != nil
		end
	end
end
