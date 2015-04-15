class Robot
	attr_reader :name
	@@robot_names = []
	
	def initialize
		set_name
	end

	def reset
		set_name(@name)
	end

	private 
	def set_name(currentName = "")
		@@robot_names.delete(currentName) unless @@robot_names.empty? 
		@name = ""
		next while @@robot_names.include?(@name = generate_random)
		@@robot_names << @name
	end

	def generate_random
		local_name = ""
		2.times{ local_name << ('A'..'Z').to_a.shuffle[0].to_s} &&
		3.times{ local_name << (0..9).to_a.shuffle[0].to_s} 
		local_name
	end
end
