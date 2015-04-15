class Robot

	SERIAL_NUM_FMT = 'AA###'
	@@name_history = Hash.new

	def initialize()
		@name = String.new 
	end

	def create_name()
		SERIAL_NUM_FMT.each_char {|d|
		if d == 'A'
			@name << rand(65..65+25).chr
		elsif d == '#'
			@name << rand(0..9).to_s
		end
		}

	end

	def name()
		if @name == ""
			self.create_name
			while @@name_history.include? @name
				self.reset
				self.create_name
			end
			@@name_history[@name] = nil
		end
		@name
	end

	def reset()
		@name = ""
	end

end

robot = Robot.new
5.times do
p robot.name
end

robot.reset

p robot.name
