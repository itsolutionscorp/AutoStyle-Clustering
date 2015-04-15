class Robot
  DIRECTIONS = { north: [0, 1], east: [1, 0], south: [0, -1], west: [-1, 0] }

  attr_reader :x, :y, :direction
  alias_method :bearing, :direction

  def initialize
    @x = @y = 0
    @direction = :north
  end

  def at(x, y)
    tap { @x, @y = x, y }
  end

  def orient(direction)
    fail ArgumentError unless DIRECTIONS.key?(direction)
    tap { @direction = direction }
  end

  def advance
    dx, dy = DIRECTIONS[direction]
    tap { @x, @y = x + dx, y + dy }
  end

  def turn_right
    tap { @direction = directions[1] }
  end

  def turn_left
    tap { @direction = directions[-1] }
  end

  def coordinates
    [x, y]
  end

  private

  def directions
    DIRECTIONS.keys.rotate(DIRECTIONS.keys.index direction)
  end
end

class Simulator
  INSTRUCTIONS = { turn_left: 'L', turn_right: 'R', advance: 'A' }

  def instructions(code)
    code.each_char.map { |c| INSTRUCTIONS.key(c) }
  end

  def place(robot, x: 0, y: 0, direction: :north)
    robot.at(x, y).orient(direction)
  end

  def evaluate(robot, code)
    instructions(code).each { |instruction| robot.send(instruction) }
  end
end
