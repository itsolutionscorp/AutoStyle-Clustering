class Robot 
  attr_reader :bearing

  DIRECTIONS = [:east, :north, :west, :south]
  ROTATIONS = {:left => 1, :right => -1}
  Position = Struct.new(:x, :y)

  def initialize()
    @bearing = :north
    @coordinates = Position.new(0, 0)
  end

  def orient(direction)
    raise ArgumentError unless DIRECTIONS.include?(direction)

    @bearing = direction
  end

  def at(x, y)
    @coordinates.x = x
    @coordinates.y = y
    self
  end

  def coordinates
    [@coordinates.x, @coordinates.y]
  end

  def advance
    case @bearing
    when :east then @coordinates.x += 1
    when :north then @coordinates.y += 1
    when :west then @coordinates.x -= 1
    when :south then @coordinates.y -= 1
    end
  end


  private
  
  def turn(direction)
    orient(DIRECTIONS.rotate(ROTATIONS[direction])[DIRECTIONS.index(@bearing)])
  end
  
  def method_missing(method)
    if method.to_s =~ /^turn_(.+)$/
      turn($1.to_sym)
    else
      super
    end
  end
  
  def respond_to?(method)
    if method.to_s =~ /^turn_(.+)$/
      true
    else
      super
    end
  end

end

class Simulator

  COMMANDS = {"L" => :turn_left, "R" => :turn_right, "A" => :advance}

  def initialize
  end

  def instructions(string)
    string.chars.inject([]){|commands, char| commands << COMMANDS[char]}
  end

  def place(robot, x: 0,  y: 0, direction: :north)
    robot.at(x, y).orient(direction)
  end

  def evaluate(robot, commands)
    instructions(commands).each {|command| robot.send(command)}
  end

end
