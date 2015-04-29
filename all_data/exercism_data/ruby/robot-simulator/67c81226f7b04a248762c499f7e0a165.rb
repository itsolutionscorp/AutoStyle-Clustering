class Robot
  attr_accessor :coordinates, :direction

  def initialize
    @valid_directions = [:north, :east, :south, :west]
  end

  def orient(direction)
    @direction = direction
    cannot_use_invalid_bearing
  end

  def bearing
    direction
  end

  def turn_right
    @direction = valid_directions[index_position - 3]
  end

  def turn_left
    @direction = valid_directions[index_position - 1]
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def advance
    if (direction == :north) || (direction == :south)
      coordinates[1] += movement
    else
      coordinates[0] += movement
    end
  end

  private

  attr_reader :valid_directions

  def movement
    {north: 1, east: 1, south: -1, west: -1}[direction]
  end

  def index_position
    valid_directions.index(direction)
  end

  def cannot_use_invalid_bearing
    unless valid_bearing?
      raise ArgumentError.new
    end
  end

  def valid_bearing?
    valid_directions.include? direction
  end
end

class Simulator
  def instructions(letters)
    instructions = []
    letters.each_char do |action|
      instructions << action_to_instruction[action]
    end
    instructions
  end

  def action_to_instruction
    {
      "L" => :turn_left,
      "R" => :turn_right,
      "A" => :advance,
    }
  end

  def place(robot, options)
    robot.direction = options[:direction]
    robot.coordinates = [options[:x], options[:y]]
  end

  def evaluate(robot, letters)
    instructions(letters).each do |instruction|
      robot.send(instruction)
    end
  end
end
