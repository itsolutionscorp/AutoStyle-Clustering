class Simulator
  def instructions(text)
    text.chars.map { |char| command(char) }
  end

  def place(robot, x: 0, y: 0, direction: north)
    robot.at(x, y)
    robot.orient(direction)
  end

  def evaluate(robot, text)
    instructions(text).each do |command|
      robot.send(command.to_s)
    end
  end

  private

  def command(char)
    {
      'R' => :turn_right,
      'L' => :turn_left,
      'A' => :advance
    }[char]
  end
end

class Robot
  def initialize
    @angle, @x, @y = 0, 0, 0
  end

  def directions
    [:east, :south, :west, :north]
  end

  def orient(direction)
    fail ArgumentError unless directions.include? direction

    @angle = (directions.index(direction)) * 90
  end

  def bearing
    directions[(@angle % 360) / 90]
  end

  def turn_right
    @angle += 90
  end

  def turn_left
    @angle -= 90
  end

  def at(x, y)
    @x, @y = x, y
  end

  def coordinates
    [@x, @y]
  end

  def advance
    case bearing
    when :north
      @y += 1
    when :south
      @y -= 1
    when :east
      @x += 1
    when :west
      @x -= 1
    end
  end
end
