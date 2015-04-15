class Robot
  DIRECTIONS = [:north, :east, :south, :west]

  def initialize
    @direction = DIRECTIONS[0]
  end

  def orient(direction)
    raise ArgumentError unless DIRECTIONS.include?(direction)
    @direction = direction
  end

  def bearing
    @direction
  end

  def turn_right
    i = DIRECTIONS.index(@direction)
    i2 = (i + 1) % DIRECTIONS.size
    @direction = DIRECTIONS[i2]
  end

  def turn_left
    i = DIRECTIONS.index(@direction)
    i2 = (i - 1) % DIRECTIONS.size
    @direction = DIRECTIONS[i2]
  end

  def at(x, y)
    @x, @y = x, y
  end

  def coordinates
    [@x, @y]
  end

  def advance
    case @direction
    when :north
      @y += 1
    when :east
      @x += 1
    when :south
      @y -= 1
    when :west
      @x -= 1
    end
  end
end

class Simulator
  INSTRCTS = { 'R' => :turn_right, 'L' => :turn_left, 'A' => :advance }

  def instructions(s)
    s.each_char.map { |c| INSTRCTS[c] }
  end

  def place(robot, **args)
    x = args[:x] || 0
    y = args[:y] || 0
    direction = args[:direction] || Robot.DIRECTIONS[0]
    robot.at(x, y)
    robot.orient(direction)
  end

  def evaluate(robot, instructions)
    instructions.each_char { |c| robot.method(INSTRCTS[c]).call }
  end
end
