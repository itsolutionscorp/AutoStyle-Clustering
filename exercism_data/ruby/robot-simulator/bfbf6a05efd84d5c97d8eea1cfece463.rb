class Robot

  DIRECTIONS = [:north, :east, :south, :west]

  attr_accessor :bearing, :coordinates

  def initialize(x=0,y=0)
    @bearing = nil
    @x, @y = x, y
    @coordinates = [@x,@y]
  end

  def orient(direction)
    raise ArgumentError unless DIRECTIONS.include?(direction)
    self.bearing = direction
  end

  def turn_right
    index = DIRECTIONS.index(bearing)
    if index == DIRECTIONS.size-1
      self.bearing = DIRECTIONS.first
    else 
      self.bearing = DIRECTIONS[index+1]
    end
  end

  def turn_left
    index = DIRECTIONS.index(bearing)
    if index == 0
      self.bearing = DIRECTIONS.last
    else 
      self.bearing = DIRECTIONS[index-1]
    end
  end

  def advance
    case bearing
    when :north then @y += 1
    when :south then @y -= 1
    when :east  then @x += 1
    when :west  then @x -= 1
    end
    @coordinates = [@x,@y]
  end

  def at(x, y)
    @x, @y = x, y
    @coordinates = [@x,@y]
  end

end

class Simulator

  INSTRUCTIONS = {
    :l => :turn_left,
    :r => :turn_right,
    :a => :advance
  }

  def place(robot, options={})
    robot.at(options[:x], options[:y])
    robot.orient(options[:direction])
  end

  def evaluate(robot, string)
    self.instructions(string).each do |instruction|
      robot.send(instruction.to_sym)
    end 
  end

  def instructions(string)
    string.chars.map(&:downcase).map(&:to_sym).map { |i| INSTRUCTIONS[i] }
  end

end
