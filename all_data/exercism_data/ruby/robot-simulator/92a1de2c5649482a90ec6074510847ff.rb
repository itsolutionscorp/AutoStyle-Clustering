require 'forwardable'

class Point
  attr_reader :x, :y
  def initialize(x,y)
    @x = x
    @y = y
  end

  def to_a
    [x, y]
  end

  def +(other_point)
    Point.new(x+other_point.x,
              y+other_point.y)
  end
end

class Robot
  extend Forwardable
  class << self
    def valid_directions
      [:north, :east, :south, :west]
    end

    def point_offset_for_bearing(direction)
      {
        north: Point.new(0, 1),
        east: Point.new(1,0),
        south: Point.new(0,-1),
        west: Point.new(-1,0)
      }[direction]
    end
  end

  attr_reader :bearing, :x, :y
  def_delegator :@at, :to_a, :coordinates

  def initialize
    @at = Point.new(0,0)
  end

  def orient(direction)
    unless Robot.valid_directions.include? direction
      raise ArgumentError
    end

    @bearing = direction
  end

  def at(x,y)
    @at = Point.new(x,y)
  end

  def advance
    @at = @at + Robot.point_offset_for_bearing(bearing)
  end

  def turn_right
    turn(1)
  end

  def turn_left
    turn(-1)
  end

  private

  def turn(degree)
    index_of_current_direction = Robot.valid_directions.index bearing
    index_of_next_direction = (index_of_current_direction+degree)%Robot.valid_directions.size
    @bearing = Robot.valid_directions[index_of_next_direction]
  end
end

class Simulator
  class << self
    def code_to_instruction
      {
        "L" => :turn_left,
        "R" => :turn_right,
        "A" => :advance
      }
    end
  end

  def instructions(instruction_code)
    instruction_code.split('').collect do |code|
      Simulator.code_to_instruction[code]
    end
  end

  def place(robot, options)
    robot.at(options[:x], options[:y])
    robot.orient(options[:direction])
  end

  def evaluate(robot, instruction_code)
    instructions(instruction_code).each do |instruction|
      robot.send(instruction)
    end
  end
end
