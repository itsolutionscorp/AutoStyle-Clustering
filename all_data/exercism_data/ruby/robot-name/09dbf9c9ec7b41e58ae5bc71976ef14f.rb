#require 'pry'

class Robot
	#attr_accessor :stick

	def name
		unless @stick == true
			@robot_name = ""
			@robot_name << 1.times.map {[*'A'..'Z'].sample(2)}.join
			@robot_name << 1.times.map {[*'1'..'9'].sample(3)}.join
			@stick = true
			@robot_name
		end
		@robot_name
	end

	def reset
			@stick = false
			self.name
	end
end

#binding.pry
