class Simulator

	INSTRUCTIONS = {
		"L" => :turn_left,
		"R" => :turn_right,
		"A" => :advance
	}

	def initialize
		
	end

	def instructions(pattern)
		pattern.chars.map { |c| INSTRUCTIONS[c] }
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
	attr_reader :coordinates

	def initialize
		@direction = Direction[:north]
	end

	def orient(direction)
		@direction = Direction[direction]
	end

	def bearing
		@direction.bearing
	end

	def turn_right
		@direction = @direction.turn_right
	end
		
	def turn_left
		@direction = @direction.turn_left
	end
	
	def at(*coordinates)
		@coordinates = coordinates
	end

	def advance
		@coordinates = @direction.move_forward(*@coordinates, 1)
	end

end


class Direction
	attr_reader :bearing
	private_class_method :new

	DIRECTIONS = {
		north: {
			move_forward: ->(x, y, n) { [x, y+n] },
			turn_left:  :west,
			turn_right: :east
		},
		east: {
			move_forward: ->(x, y, n) { [x+n, y] },
			turn_left:  :north,
			turn_right: :south
		},
		south: {
			move_forward: ->(x, y, n) { [x, y-n] },
			turn_left:  :east,
			turn_right: :west
		},
		west: {
			move_forward: ->(x, y, n) { [x-n, y] },
			turn_left:  :south,
			turn_right: :north
		}
	}

	def self.validate_direction(direction)
		raise ArgumentError unless DIRECTIONS.keys.include? direction
	end

	def initialize(direction)
		self.class.validate_direction(direction)
		@bearing = direction
	end

	@@directions = DIRECTIONS.keys.each_with_object({}) do |dir, result|
		result[dir] = new(dir)
	end

	def self.[](direction)
		validate_direction(direction)
		@@directions[direction]
	end

	private

		def self.symbol_passthrough(key)
			define_method(key) do
				@@directions[DIRECTIONS[@bearing][key]]
			end
		end

		def self.proc_passthrough(key)
			define_method(key) do |*args|
				DIRECTIONS[@bearing][key].call(*args)
			end
		end

		def self.passthrough(key, value)
			send("#{value.class.to_s.downcase}_passthrough", key)
		end

		DIRECTIONS.first.last.each { |key, value| self.passthrough(key, value) }

end
