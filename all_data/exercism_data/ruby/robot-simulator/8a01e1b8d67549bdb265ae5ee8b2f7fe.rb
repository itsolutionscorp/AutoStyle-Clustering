class Simulator

	INSTRUCTION_SET = {
		"L" => :turn_left,
		"R" => :turn_right,
		"A" => :advance
	}

	def instructions(pattern)
		pattern.chars.map { |c| INSTRUCTION_SET[c] }
	end

	def place(robot, x: 0, y: 0, direction: :north)
		robot.orient(direction)
		robot.at(x, y)
	end

	def evaluate(robot, pattern)
		instructions(pattern).each do |instruction|
			robot.send(instruction)
		end
	end
	
end


class Robot
	attr_reader :x, :y

	def initialize
		orient(:north)
		at(0, 0)
	end

	def bearing
		@direction.name
	end

	def orient(direction)
		@direction = DIRECTIONS.fetch(direction) { raise ArgumentError }
		self
	end

	def turn_right
		orient @direction.right
	end
		
	def turn_left
		orient @direction.left
	end
	
	def at(x, y)
		@x, @y = x, y
		self
	end

	def coordinates
		[x, y]
	end

	def advance
		at(x + @direction.x, y + @direction.y)
	end

	private

		class Direction < Struct.new(:name, :right, :left, :x, :y)
		end

		DIRECTIONS = {
			north: Direction.new(:north, :east,  :west,   0,  1),
			east:  Direction.new(:east,  :south, :north,  1,  0),
			south: Direction.new(:south, :west,  :east,   0, -1),
			west:  Direction.new(:west,  :north, :south, -1,  0)
		}

end
