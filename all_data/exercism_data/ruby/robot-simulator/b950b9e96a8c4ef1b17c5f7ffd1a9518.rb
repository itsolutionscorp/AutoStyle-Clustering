class Coordinates
  attr_reader :x, :y

  def initialize(x, y)
    @x, @y = x, y
  end

  def to_a
    [x, y]
  end

  def up
    Coordinates.new(x, y + 1)
  end

  def right
    Coordinates.new(x + 1, y)
  end

  def down
    Coordinates.new(x, y - 1)
  end

  def left
    Coordinates.new(x - 1, y)
  end
end

class Directions

  def initialize
    north = Direction.new(:north, :up)
    east = Direction.new(:east, :right)
    south = Direction.new(:south, :down)
    west = Direction.new(:west, :left)

    north.neighbors = [west, east]
    east.neighbors = [north, south]
    south.neighbors = [east, west]
    west.neighbors = [south, north]

    @all = {
      north: north,
      east: east,
      south: south,
      west: west,
    }
  end

  def for(sym)
    @all[sym] || raise(ArgumentError)
  end
end

class Direction

  attr_reader :left, :right, :name, :relative_name

  def initialize(name, relative_name)
    @name, @relative_name = name, relative_name
  end

  def neighbors=(neighbors)
    @left, @right = neighbors
  end

  def to_sym
    name.to_sym
  end

end

class Robot

  def orient(sym)
    @direction = Directions.new.for(sym)
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
    @coordinates = Coordinates.new(x, y)
  end

  def coordinates
    @coordinates.to_a
  end

  def advance
    @coordinates = @coordinates.send(@direction.relative_name)
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
