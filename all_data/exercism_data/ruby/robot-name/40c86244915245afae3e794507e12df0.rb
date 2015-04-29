class Robot
	attr_reader :name

	def initialize
		self.reset
	end

	def reset
		set_name
	end

	private

	def set_name
		@name = (('A'..'Z').to_a.sample(2) << (
			0..9).to_a.sample(3)).join('')
	end

end
