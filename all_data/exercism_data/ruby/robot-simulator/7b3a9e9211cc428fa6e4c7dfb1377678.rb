class Robot
  attr_reader :bearing, :coordinates

  def initialize
    @compass = [:north, :east, :south, :west]
    set_bearing
    at 0, 0
  end

  def orient direction
    fail ArgumentError unless @compass.include? direction
    @compass.rotate! until @compass.first == direction
    set_bearing
  end

  def turn_right
    @compass.rotate!
    set_bearing
  end

  def turn_left
    @compass.rotate!(-1)
    set_bearing
  end

  def at x, y
    @coordinates = [x, y]
  end

  def advance
    case bearing
    when :north
      @coordinates[1] += 1
    when :east
      @coordinates[0] += 1
    when :south
      @coordinates[1] -= 1
    when :west
      @coordinates[0] -= 1
    end
  end

  private

  def set_bearing
    @bearing = @compass.first
  end
end

class Simulator
  ACTIONS = { turn_left: 'L', turn_right: 'R', advance: 'A' }

  def instructions str
    str.chars.map { |c| ACTIONS.key c }
  end

  def place robot, options
    robot.at options[:x], options[:y]
    robot.orient options[:direction]
  end

  def evaluate robot, instruction_set
    instructions(instruction_set).each { |i| robot.send i }
  end
end
