DIRECTIONS = [:north, :east, :south, :west]
COMMANDS =  
{
	'R' => :turn_right,
	'L' => :turn_left,
	'A' => :advance,
}

class Simulator
	def initialize()
	end
	
	def instructions(text)
		text.chars.collect{|c| COMMANDS[c]}
	end
	
	def place(robot, x:, y:, direction:)
		robot.at(x, y)
		robot.orient(direction)
	end
	
	def evaluate(robot, text)
		instructions(text).each{|command| robot.send(command)}
	end
end

class Robot
	def initialize()
		@direction = 0
		@x = 0
		@y = 0
	end
	
	def bearing()
		DIRECTIONS[@direction]
	end
	
	def coordinates()
		[@x, @y]
	end
	
	def orient(new_direction)
		raise(ArgumentError) unless DIRECTIONS.include?(new_direction)
		@direction = DIRECTIONS.index(new_direction)
	end
	
	def at(new_x, new_y)
		@x = new_x
		@y = new_y
	end
	
	def turn_right()
		@direction = (@direction + 1) % 4
	end
	
	def turn_left()
		@direction = (@direction - 1 + 4) % 4
	end
	
	def advance()
		multiplier = (@direction <= 1) ? 1 : -1
		if @direction.even?
			@y += 1 * multiplier
		else
			@x += 1 * multiplier
		end
	end
end
