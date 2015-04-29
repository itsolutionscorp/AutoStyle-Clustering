class Robot
	@@used_names = []

	def initialize
		@name = rando_name
		@@used_names << @name
	end

	def rando_name
		letts = ('A'..'Z').to_a.sample(2).join
		nums = ('0'..'9').to_a.sample(3).join
		letts + nums
	end

	def reset
		new_name = rando_name()
		while @@used_names.index(new_name) != nil
			new_name = rando_name()
		end
		@name = new_name
		@@used_names << @name
	end

	attr_reader :name
end
