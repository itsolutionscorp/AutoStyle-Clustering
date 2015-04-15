class Robot
  attr_reader :bearing, :coordinates

  DIRECTIONS = [:east, :south, :west, :north]

  def initialize
    @bearing = :east
    @coordinates = []
  end

  def orient(direction)
    raise ArgumentError unless DIRECTIONS.include?(direction)
    @bearing = direction
  end

  def turn_right
    current_idx = DIRECTIONS.index(bearing)
    @bearing = DIRECTIONS.at (current_idx + 1) % DIRECTIONS.size
  end

  def turn_left
    current_idx = DIRECTIONS.index(bearing)
    @bearing = DIRECTIONS.at (current_idx - 1) % DIRECTIONS.size
  end

  def at(x,y)
    @coordinates = [x, y]
  end

  def advance
    case bearing
      when :north then @coordinates[1] += 1
      when :south then @coordinates[1] -= 1
      when :east  then @coordinates[0] += 1
      when :west  then @coordinates[0] -= 1
    end
  end

end

class Simulator
  def instructions(directions)
    directions.split(//).reduce([]) do |ary, direction|
      ary << translate_instructions.fetch(direction)
    end
  end

  def translate_instructions
    {
      "L" => :turn_left,
      "R" => :turn_right,
      "A" => :advance
    }
  end

  def place(robot, options)
    robot.at(options[:x], options[:y])
    robot.orient(options[:direction])
  end

  def evaluate(robot, directions)
    instructions(directions).each do |instruction|
      robot.send(instruction)
    end
  end
end
