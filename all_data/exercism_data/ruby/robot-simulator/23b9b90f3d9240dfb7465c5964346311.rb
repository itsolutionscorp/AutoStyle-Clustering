require 'delegate'

class CircularArray < SimpleDelegator
  def [](index)
    super(index % length)
  end
end

class Robot
  attr_reader :bearing
  attr_reader :coordinates

  def orient(direction)
    validate_direction(direction)
    @bearing = direction
  end

  def turn_right
    orient(next_direction(bearing))
  end

  def turn_left
    orient(previous_direction(bearing))
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def advance
    at(*sum_coordinates(coordinates, direction_offsets[bearing]))
  end

  private

  def validate_direction(direction)
    raise ArgumentError unless valid_directions.include?(direction)
  end

  def valid_directions
    @valid_directions ||= CircularArray.new([:north, :east, :south, :west])
  end

  def next_direction(direction)
    valid_directions[valid_directions.index(direction) + 1]
  end

  def previous_direction(direction)
    valid_directions[valid_directions.index(direction) - 1]
  end

  def direction_offsets
    {
      north: [0,  1],
      east:  [1,  0],
      south: [0, -1],
      west:  [-1, 0]
    }
  end

  def sum_coordinates(first, second)
    first.zip(second).map do |ary|
      ary.inject(:+)
    end
  end
end

class Simulator
  def instructions(command)
    command.chars.map do |c|
      command_map[c]
    end
  end

  def place(robot, x: 0, y: 0, direction: :north)
    robot.at(x, y)
    robot.orient(direction)
  end

  def evaluate(robot, command)
    instructions(command).each do |sym|
      robot.send(sym)
    end
  end

  private

  def command_map
    {
      "L" => :turn_left,
      "R" => :turn_right,
      "A" => :advance
    }
  end

end
