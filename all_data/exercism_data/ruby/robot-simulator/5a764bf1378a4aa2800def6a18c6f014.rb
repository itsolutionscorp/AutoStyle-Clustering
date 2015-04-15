class Simulator
  INSTRUCTION_MAP = {
    'L' => :turn_left,
    'R' => :turn_right,
    'A' => :advance
  }

  def instructions(letters)
    letters.each_char.map { |letter| INSTRUCTION_MAP[letter] }
  end

  def place(robot, x:, y:, direction:)
    robot.at(x, y).orient(direction)
  end

  def evaluate(robot, letters)
    instructions(letters).each do |instruction|
      robot.public_send(instruction)
    end
  end
end

class Robot
  Direction = Struct.new(:name, :left, :right, :advance_x, :advance_y)

  DIRECTIONS = {
    north: Direction.new(:north, :west, :east, 0, 1),
    east:  Direction.new(:east, :north, :south, 1, 0),
    south: Direction.new(:south, :east, :west, 0, -1),
    west:  Direction.new(:west, :south, :north, -1, 0)
  }

  def bearing
    direction.name
  end

  def coordinates
    [x, y]
  end

  def orient(direction)
    @direction = DIRECTIONS.fetch(direction) { raise ArgumentError }
    self
  end

  def turn_right
    orient(direction.right)
    self
  end

  def turn_left
    orient(direction.left)
    self
  end

  def at(x, y)
    @x, @y = x, y
    self
  end

  def advance
    at(x + direction.advance_x, y + direction.advance_y)
    self
  end

  private

  attr_reader :direction, :x, :y
end
