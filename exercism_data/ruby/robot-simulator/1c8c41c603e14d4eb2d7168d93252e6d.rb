class Robot
  attr_reader :bearing

  DIRECTIONS = [:east, :north, :west, :south]
  LEFT = Hash.new do |left, direction|
    DIRECTIONS[(DIRECTIONS.index(direction) + 1) % 4]
  end

  RIGHT = Hash.new do |right, direction|
    DIRECTIONS[(DIRECTIONS.index(direction) + 3) % 4]
  end

  def orient(direction)
    unless DIRECTIONS.include? direction
      raise(ArgumentError, "Invalid direction: #{direction}")
    end

    @bearing = direction
  end

  def turn_right
    @bearing = RIGHT[@bearing]
  end

  def turn_left
    @bearing = LEFT[@bearing]
  end

  def at(x, y)
    @x, @y = [x, y]
  end

  def coordinates
    [@x, @y]
  end

  def advance
    send ("go_" + @bearing.to_s).to_sym
  end

  private
  def go_north
    @y += 1
  end

  def go_east
    @x += 1
  end

  def go_south
    @y -= 1
  end

  def go_west
    @x -= 1
  end
end

class Simulator
  COMMAND = {
    "L" => :turn_left,
    "R" => :turn_right,
    "A" => :advance
  }

  def instructions(command_string)
    command_string.scan(/[A-Z]/).map { |code| COMMAND[code] }
  end

  def place(robot, x: 0, y: 0, direction: :north)
    robot.at x, y
    robot.orient direction
  end

  def evaluate(robot, command_string)
    instructions(command_string).each do |command|
      robot.send command
    end
  end
end
