require 'set'

class Robot

	SERIAL_NUM_FMT = 'AA###'
	@@name_history = Set.new

	def create_name()
		@name = String.new
		SERIAL_NUM_FMT.each_char {|d|
		if d == 'A'
			@name << rand(65..65+25).chr
		elsif d == '#'
			@name << rand(0..9).to_s
		end
		}

		while @@name_history.include? @name
			create_name
		end
		@@name_history.add(@name)
		@name
	end

	def name()
		@name ||= create_name
	end

	def reset()
		@name = nil 
	end

end
