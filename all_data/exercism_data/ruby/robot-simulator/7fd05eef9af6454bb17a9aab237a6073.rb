class Robot

  attr_reader :bearing, :x, :y

  ORIENTATIONS = %i( east north west south )

  def orient direction
    raise ArgumentError unless ORIENTATIONS.include? direction
    @bearing = direction
  end

  def at(x, y)
    @x, @y = x, y
  end

  def coordinates
    [@x, @y]
  end

  def turn_right
    turn(-1)
  end

  def turn_left
    turn(1)
  end

  def turn(movement)
    @bearing = ORIENTATIONS[(ORIENTATIONS.index(@bearing) + movement) % 4]
  end
  private :turn

  def advance
    case @bearing
    when :east  then @x += 1
    when :north then @y += 1
    when :west  then @x -= 1
    when :south then @y -= 1
    end
  end

end

class Simulator

  INSTRUCTIONS = {
    "L" => :turn_left,
    "R" => :turn_right,
    "A" => :advance,
  }

  def instructions instructions
    instructions.chars.map { |i| INSTRUCTIONS[i] }
  end

  def place robot, x: 0, y: 0, direction: :north
    robot.at(x,y)
    robot.orient(direction)
  end

  def evaluate robot, orders
    instructions(orders).each { |i| robot.send(i) }
  end

end
