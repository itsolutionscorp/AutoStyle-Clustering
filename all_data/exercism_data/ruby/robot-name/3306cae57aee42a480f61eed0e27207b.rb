class Robot
	@@index = 0
	attr_reader :name
	def initialize
		set_name
	end

	def reset
		set_name
	end

	private

	def set_name
		@name = Robot.get_name
	end

	def self.get_name
		@@index += 1
		"ab00" + @@index.to_s
	end
end
