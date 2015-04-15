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
  DIRECTIONS_CLOCKWISE = {
    north: :east,
    east:  :south,
    south: :west,
    west:  :north
  }

  attr_reader :bearing, :coordinates

  def orient(direction)
    raise ArgumentError unless DIRECTIONS_CLOCKWISE.keys.include?(direction)
    @bearing = direction
  end

  def turn_right
    @bearing = DIRECTIONS_CLOCKWISE[bearing]
    self
  end

  def turn_left
    @bearing = DIRECTIONS_CLOCKWISE.invert[bearing]
    self
  end

  def at(x, y)
    @coordinates = [x, y]
    self
  end

  def advance
    case bearing
    when :north then @coordinates[1] += 1
    when :east  then @coordinates[0] += 1
    when :south then @coordinates[1] -= 1
    when :west  then @coordinates[0] -= 1
    end
    self
  end
end
