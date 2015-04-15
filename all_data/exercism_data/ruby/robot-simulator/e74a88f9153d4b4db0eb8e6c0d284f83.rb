class Robot
  DIRECTIONS = %i[north east south west]
  OFFSETS = [[0, 1], [1, 0], [0, -1], [-1, 0]]

  attr_reader :bearing

  def orient(bearing)
    raise ArgumentError unless DIRECTIONS.member?(bearing)
    @bearing = bearing
  end

  def turn_right
    turn(:+)
  end

  def turn_left
    turn(:-)
  end

  def at(row, column)
    @row, @column = row, column
  end

  def coordinates
    [@row, @column]
  end

  def advance
    direction_index = get_direction_index
    row_offset, column_offset = OFFSETS[direction_index]
    @row += row_offset
    @column += column_offset
  end

  private

  def get_direction_index
   DIRECTIONS.index(bearing)
  end

  def turn(operator)
    direction_index = get_direction_index
    direction_index = (direction_index.public_send(operator, 1)) % 4
    orient(DIRECTIONS[direction_index])
  end
end

class Simulator
  CODES = { "L" => :turn_left, "R" => :turn_right, "A" => :advance}

  def place(robot, x:, y:, direction:)
    robot.at(x, y)
    robot.orient(direction)
  end

  def evaluate(robot, codes)
    instructions(codes).each do |instruction|
      robot.public_send(instruction)
    end
  end

  def instructions(codes)
    codes.chars.map { |code| CODES[code] }
  end
end
