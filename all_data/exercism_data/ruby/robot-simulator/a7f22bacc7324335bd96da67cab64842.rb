class North
  def to_sym
    :north
  end

  def right
    East.new
  end

  def left
    West.new
  end

  def advance(coordinates)
    x, y = coordinates
    [x, y + 1]
  end
end

class East
  def to_sym
    :east
  end

  def right
    South.new
  end

  def left
    North.new
  end

  def advance(coordinates)
    x, y = coordinates
    [x + 1, y]
  end
end

class South
  def to_sym
    :south
  end

  def right
    West.new
  end

  def left
    East.new
  end

  def advance(coordinates)
    x, y = coordinates
    [x, y - 1]
  end
end

class West
  def to_sym
    :west
  end

  def right
    North.new
  end

  def left
    South.new
  end

  def advance(coordinates)
    x, y = coordinates
    [x - 1, y]
  end
end

class Direction
  DIRECTION_CLASSES = {north: North, east: East, south: South, west: West }

  def self.for(sym)
    raise ArgumentError unless DIRECTION_CLASSES.keys.include?(sym)
    DIRECTION_CLASSES[sym].new
  end
end

class Robot

  def orient(sym)
    @direction = Direction.for(sym)
  end

  def bearing
    @direction.to_sym
  end

  def turn_right
    @direction = @direction.right
  end

  def turn_left
    @direction = @direction.left
  end

  def at(x, y)
    @coordinates = [x, y]
  end

  def coordinates
    @coordinates
  end

  def advance
    @coordinates = @direction.advance(@coordinates)
  end
end

class Simulator
  MAPPINGS = {'L' => :turn_left, 'R' => :turn_right, 'A' => :advance}

  def instructions(input)
    input.each_char.map { |c| MAPPINGS[c] }
  end

  def place(robot, x: nil, y: nil, direction: nil)
    robot.orient(direction)
    robot.at(x, y)
  end

  def evaluate(robot, input)
    instructions(input).each do |instruction|
      robot.send(instruction)
    end
  end
end
