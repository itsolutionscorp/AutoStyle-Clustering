class Simulator
  INSTRUCTION_KEY = {
    turn_left: "L",
    turn_right: "R",
    advance: "A",
  }

  def place(robot, opts={})
    robot.at(opts[:x],opts[:y])
    robot.orient(opts[:direction])
  end

  def evaluate(robot,sequence)
    instructions(sequence).each do |step|
      robot.send(step)
    end
  end

  def instructions(sequence)
    sequence.chars.map { |step| INSTRUCTION_KEY.invert[step] }
  end
end

class Robot
  DIRECTIONS = [:north,:east,:south,:west]
  DIRECTION_INSTRUCTIONS = {
    north: lambda { |x,y| [x,y+1] },
    east: lambda { |x,y| [x+1,y] },
    south: lambda { |x,y| [x,y-1] },
    west: lambda { |x,y| [x-1,y] }
  }

  attr_reader :bearing, :coordinates

  def advance
    @coordinates = DIRECTION_INSTRUCTIONS[@bearing].call(*@coordinates)
  end

  def at(*coords)
    @coordinates = coords
  end

  def orient(direction)
    @bearing = validate_direction(direction)
  end

  def turn_right
    @bearing = DIRECTIONS[(DIRECTIONS.index(@bearing)+1) % 4]
  end

  def turn_left
    @bearing = DIRECTIONS[(DIRECTIONS.index(@bearing)-1) % 4]
  end

  private

  def validate_direction(direction)
    return direction if DIRECTIONS.any? {|d| d == direction}
    fail ArgumentError, "Invalid direction"
  end
end
