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

class DirectionFactory

  def self.all
    @all ||= begin
      north = Direction.new(:north, :up)
      south = Direction.new(:south, :down)
      east = Direction.new(:east, :right)
      west = Direction.new(:west, :left)

      north.neighbors = [west, east]
      south.neighbors = [east, west]
      east.neighbors = [north, south]
      west.neighbors = [south, north]

      {
        north: north,
        east: east,
        south: south,
        west: west,
      }
    end
  end

  def self.for(sym)
    all[sym] || raise(ArgumentError)
  end
end

class Robot

  def orient(sym)
    @direction = DirectionFactory.for(sym)
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
