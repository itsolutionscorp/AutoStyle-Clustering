class Robot
  DIRECTIONS = [
    :north,
    :east,
    :south,
    :west
  ]

  attr_accessor :bearing, :x, :y

  def orient bearing
    if DIRECTIONS.include? bearing
      @bearing = bearing
    else
      raise ArgumentError
    end
  end

  def turn_right
    orient_by_index index(bearing) + 1
  end

  def turn_left
    orient_by_index index(bearing) - 1
  end

  def at x, y
    @x = x
    @y = y
  end

  def coordinates
    [x, y]
  end

  def advance
    if bearing == :north
      @y += 1
    elsif bearing == :south
      @y -= 1
    elsif bearing == :east
      @x += 1
    else
      @x -= 1
    end
  end

  private
  def orient_by_index index
    @bearing = DIRECTIONS[index % 4]
  end

  def index bearing
    DIRECTIONS.index bearing
  end
end

class Simulator
  COMMANDS = {
    'L'=>:turn_left,
    'R'=>:turn_right,
    'A'=>:advance
  }

  def instructions commands
    commands.chars.map { |char| COMMANDS[char] }
  end

  def place robot, opts = {}
    robot.at opts[:x], opts[:y]
    robot.orient opts[:direction]
  end

  def evaluate robot, commands
    instructions(commands).each { |command|
      robot.send command
    }
  end
end
