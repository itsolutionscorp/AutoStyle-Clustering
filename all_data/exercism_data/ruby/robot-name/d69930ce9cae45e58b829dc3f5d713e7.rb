class Robot
	attr_reader :name
	def initialize
		@name = get_name()
	end
	def reset
		@name = get_name
	end

	private 
	def get_name()
		name = ""
		2.times{ name += rand(65..90).chr }
		name += (0..9).to_a.sample(3).join
	end
end
