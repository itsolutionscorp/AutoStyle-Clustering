class Simulator
  def instructions(commands)
    commands.chars.map { |command| instruction(command) }
  end

  def instruction(command)
    {
      'R' => :turn_right,
      'L' => :turn_left,
      'A' => :advance
    }[command]
  end

  def place(robot, x: 0, y: 0, direction: north)
    robot.at(x, y)
    robot.orient(direction)
  end

  def evaluate(robot, commands)
    instructions(commands).each do |instruction|
      robot.send(instruction.to_s)
    end
  end
end

class Robot
  DIRECTIONS = [:east, :south, :west, :north]
  def initialize
    @angle, @x, @y = 0, 0, 0
  end

  def orient(direction)
    fail ArgumentError unless DIRECTIONS.include? direction

    @angle = (DIRECTIONS.index(direction)) * 90
  end

  def bearing
    DIRECTIONS[(@angle % 360) / 90]
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
