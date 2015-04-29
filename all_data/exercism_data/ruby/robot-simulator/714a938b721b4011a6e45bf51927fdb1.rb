require 'forwardable'

class Robot
  extend Forwardable

  # setters (return self)
  def orient(direction)
    tap do
      @direction = DIRECTIONS.fetch(direction) { raise ArgumentError, "Invalid direction (#{direction})" }
    end
  end

  def at(x, y)
    tap { @x, @y = x, y }
  end

  # queries
  def bearing
    direction.name
  end

  def coordinates
    [x, y]
  end

  # commands (return self from setters)
  def turn_right
    orient right
  end

  def turn_left
    orient left
  end

  def advance
    at(x + advance_x, y + advance_y)
  end

  private

  attr_accessor :direction, :x, :y
  def_delegators :direction, :advance_x, :advance_y, :right, :left

  class Direction < Struct.new(:name, :right, :left, :advance_x, :advance_y)
  end

  DIRECTIONS = {
    east:  Direction.new(:east, :south, :north, 1, 0),
    south: Direction.new(:south, :west, :east, 0, -1),
    west:  Direction.new(:west, :north, :south, -1, 0),
    north: Direction.new(:north, :east, :west, 0, 1)
  }

end

class Simulator
  def instructions(string)
    string.chars.map(&method(:instruction))
  end

  def place(robot, options)
    robot.at(*options.values_at(:x, :y)).orient(options[:direction])
  end

  def evaluate(robot, commands)
    instructions(commands).each do |instruction_sym|
      robot.send instruction_sym
    end
  end

  private

  def instruction(char)
    INSTRUCTIONS.fetch(char) do
      raise ArgumentError, "Invalid instruction (#{char})"
    end
  end

  INSTRUCTIONS = {
    "L" => :turn_left,
    "R" => :turn_right,
    "A" => :advance
  }
end
