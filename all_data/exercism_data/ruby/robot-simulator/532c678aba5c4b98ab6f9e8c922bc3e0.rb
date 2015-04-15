class Robot
  DIRECTIONS_TO_X_AND_Y_ADVANCEMENT = {
    north: [0,  1],
    east:  [1,  0],
    south: [0, -1],
    west:  [-1, 0],
  }

  def orient(direction)
    validate_direction(direction)
    @bearing = direction
  end

  def bearing
    @bearing
  end

  def at(x, y)
    @x, @y = x, y
  end

  def coordinates
    [@x, @y]
  end

  def advance
    ax, ay = DIRECTIONS_TO_X_AND_Y_ADVANCEMENT[bearing]
    @x += ax
    @y += ay
  end

  def turn_right
    orient_by_index (directions.index(bearing) + 1) % directions.length
  end

  def turn_left
    orient_by_index directions.index(bearing) - 1
  end

  private

  def orient_by_index(i)
    orient directions[i]
  end

  def directions
    DIRECTIONS_TO_X_AND_Y_ADVANCEMENT.keys
  end

  def validate_direction(direction)
    unless directions.include?(direction)
      raise ArgumentError, "Unknown direction."
    end
  end
end


class Simulator
  MAP = {
    "L" => :turn_left,
    "R" => :turn_right,
    "A" => :advance
  }

  def place(robot, x: 0, y: 0, direction: :north)
    robot.at(x, y)
    robot.orient(direction)
  end

  def evaluate(robot, instruction_string)
    instructions(instruction_string).each do |instruction|
      robot.public_send(instruction)
    end
  end

  def instructions(string)
    string.chars.map { |char| MAP.fetch(char) }
  end
end
