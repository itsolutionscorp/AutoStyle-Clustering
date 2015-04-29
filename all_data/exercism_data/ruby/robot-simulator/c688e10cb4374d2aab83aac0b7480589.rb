class Robot

  DIRECTIONS = [:north, :west, :south, :east]

  attr_reader :bearing, :coordinates

  def orient(direction)
    raise ArgumentError, "Invalid direction" unless valid?(direction)
    @bearing = direction
  end

  def turn_right
    orient(DIRECTIONS[to_right])
  end

  def turn_left
    orient(DIRECTIONS[to_left])
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def advance
    send("move_#{bearing.to_s}")
  end

  private

  def valid?(direction)
    DIRECTIONS.include?(direction)
  end

  def to_right
    (DIRECTIONS.index(bearing) - 1) % 4
  end

  def to_left
    (DIRECTIONS.index(bearing) + 1) % 4
  end

  def move_north
    coordinates[1] += 1
  end

  def move_east
    coordinates[0] += 1
  end

  def move_south
    coordinates[1] -= 1
  end

  def move_west
    coordinates[0] -= 1
  end

end

class Simulator

  COMMANDS = {
    "L" => :turn_left,
    "R" => :turn_right,
    "A" => :advance
  }

  def instructions(command_list)
    command_list.chars.collect { |instruction| COMMANDS[instruction] }
  end

  def place(robot, options)
    robot.at(options[:x], options[:y])
    robot.orient(options[:direction])
  end

  def evaluate(robot, command_list)
    instructions(command_list).each { |command| robot.send(command) }
  end

end
