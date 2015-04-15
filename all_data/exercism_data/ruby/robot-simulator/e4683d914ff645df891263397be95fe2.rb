class Robot

  DIRECTIONS = {
    north: ->(x, y) { [x, y + 1] },
    east: ->(x, y) { [x + 1, y] },
    south: ->(x, y) { [x, y - 1] },
    west: ->(x, y) { [x - 1, y] },
  }.freeze

  CLOCKWISE = {north: :east, east: :south, south: :west, west: :north}.freeze
  COUNTER_CLOCKWISE = {north: :west, west: :south, south: :east, east: :north}.freeze

  def orient(sym)
    @direction = DIRECTIONS[sym] ? sym : raise(ArgumentError)
  end

  def bearing
    @direction
  end

  def turn_right
    @direction = CLOCKWISE[@direction]
  end

  def turn_left
    @direction = COUNTER_CLOCKWISE[@direction]
  end

  def at(x, y)
    @x, @y = x, y
  end

  def coordinates
    [@x, @y]
  end

  def advance
    proc = DIRECTIONS[@direction]
    @x, @y = proc.call(@x, @y)
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
