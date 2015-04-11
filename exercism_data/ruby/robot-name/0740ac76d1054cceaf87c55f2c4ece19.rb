class Robot

	def initialize
		@name = robot_name
	end

	def name
		@name
	end

	def reset
		initialize
	end

	private

	def robot_name
		name = ''
		2.times { name += ('A'..'Z').to_a.sample }
		3.times { name += rand(10).to_s }
		name
	end

end
