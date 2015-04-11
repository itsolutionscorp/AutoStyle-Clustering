class Robot

  attr_reader :bearing, :coordinates

  def orient(orientation)
    raise ArgumentError unless directions.include?(orientation)
    @bearing = orientation
  end

  def turn_right
    i = (bearing_index + 1) % 4
    @bearing = directions[i]
  end

  def turn_left
    i = (bearing_index - 1) % 4
    @bearing = directions[i]
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def advance
    advances[bearing].call
  end

  private

  def directions
    [:north, :east, :south, :west]
  end

  def bearing_index
    directions.index(bearing)
  end

  def advances
    { :north => lambda { @coordinates[1] += 1 },
      :east => lambda { @coordinates[0] += 1 },
      :south => lambda { @coordinates[1] -= 1 },
      :west => lambda { @coordinates[0] -= 1 }
    }
  end

end

class Simulator
  def instructions(commands)
    commands.chars.map do |command|
      dictionary[command]
    end
  end

  def place(robot, options={})
    robot.at(options[:x], options[:y])
    robot.orient(options[:direction])
  end

  def evaluate(robot, commands)
    instructions(commands).each do |command|
      robot.send(command)
    end
  end

  private

  def dictionary
    { "L" => :turn_left,
      "R" => :turn_right,
      "A" => :advance
    }
  end
end
