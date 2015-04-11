class Robot
  attr_reader :bearing
  attr_accessor :coordinates
  VALID_DIRECTIONS = [:north, :east, :south, :west]

  def orient(direction)
    raise ArgumentError unless VALID_DIRECTIONS.include?(direction)
    @bearing = direction
  end

  def turn_right
    @bearing = VALID_DIRECTIONS[(VALID_DIRECTIONS.index(@bearing) + 1) % VALID_DIRECTIONS.count]
  end

  def turn_left
    @bearing = VALID_DIRECTIONS[(VALID_DIRECTIONS.index(@bearing) - 1) % VALID_DIRECTIONS.count]
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def advance
    case @bearing
    when :north
      @coordinates[1] += 1
    when :east
      @coordinates[0] += 1
    when :south
      @coordinates[1] -= 1
    when :west
      @coordinates[0] -= 1
    end
  end
end

class Simulator
  def instructions(string)
    commands = []
    string.split(//).each do |char|
      case char
      when "A"
        commands << :advance
      when "R"
        commands << :turn_right
      when "L"
        commands << :turn_left
      end
    end
    return commands
  end

  def place(robot_name, locate = {})
    robot_name.at(locate[:x], locate[:y])
    robot_name.orient(locate[:direction])
  end

  def evaluate(robot_name, instructions_string)
    commands = self.instructions(instructions_string)
    commands.each { |command| robot_name.method(command).call }
  end
end
