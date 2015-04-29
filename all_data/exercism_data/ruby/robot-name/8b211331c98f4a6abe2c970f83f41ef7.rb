class Robot
	def name
		@name ||= make_name
	end

	def reset
		@name = nil
	end

	private

	def make_name
		name = ('A'..'Z').to_a.sample(2).join
		name << (0..9).to_a.sample(3).join
	end
end

bob = Robot.new
bob.name
bob.reset
