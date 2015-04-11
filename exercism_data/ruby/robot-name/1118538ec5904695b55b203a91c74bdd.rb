class Robot
	attr_accessor :name
	def initialize
		set_name
	end

	def reset
		set_name
	end

	private 
	def set_name
		@name = ''
		2.times{ @name +=('A'..'Z').to_a.shuffle[0].to_s}
		3.times{ @name +=(0..9).to_a.shuffle[0].to_s}
	end
end
