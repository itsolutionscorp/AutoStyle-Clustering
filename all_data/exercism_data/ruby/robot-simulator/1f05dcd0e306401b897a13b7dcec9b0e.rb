class Robot
  attr_accessor :coordinates, :bearing

  def initialize
    @valid_directions = [:north, :east, :south, :west]
  end

  def orient(direction)
    cannot_use_invalid_bearing(direction)
    @bearing = direction
  end

  def turn_right
    self.bearing = valid_directions[index_position - 3]
  end

  def turn_left
    self.bearing = valid_directions[index_position - 1]
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def advance
    if (bearing == :north) || (bearing == :south)
      coordinates[1] += movement
    else
      coordinates[0] += movement
    end
  end

  private

  attr_reader :valid_directions

  def movement
    {north: 1, east: 1, south: -1, west: -1}[bearing]
  end

  def index_position
    valid_directions.index(bearing)
  end

  def cannot_use_invalid_bearing(direction)
    unless valid_bearing?(direction)
      raise ArgumentError.new
    end
  end

  def valid_bearing?(direction)
    valid_directions.include? direction
  end
end

class Simulator
  ACTION_TO_INSTRUCTION =   {
    "L" => :turn_left,
    "R" => :turn_right,
    "A" => :advance,
  }

  def instructions(letters)
    letters.each_char.map do |action|
      ACTION_TO_INSTRUCTION[action]
    end
  end

  def place(robot, options)
    robot.bearing = options[:direction]
    robot.coordinates = [options[:x], options[:y]]
  end

  def evaluate(robot, letters)
    instructions(letters).each do |instruction|
      robot.send(instruction)
    end
  end
end
