class Robot

	attr_reader :name

	def initialize()
		@name = get_random_name
	end

	def get_random_name()
		random_name = 2.times.map { [*'A'..'Z'].sample }.join + 3.times.map { [*'0'..'9'].sample }.join
	end

	def reset()
		initialize
	end
end
