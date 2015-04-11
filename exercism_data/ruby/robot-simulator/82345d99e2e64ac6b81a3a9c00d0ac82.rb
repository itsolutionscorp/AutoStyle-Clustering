class Robot
  def orient(direction)
    @bearing_index = DIRECTIONS.find_index(direction) or raise ArgumentError
  end

  def turn_right
    @bearing_index = (@bearing_index + 1) % DIRECTIONS.length
  end

  def turn_left
    @bearing_index = (@bearing_index - 1) % DIRECTIONS.length
  end

  def bearing
    DIRECTIONS[@bearing_index]
  end

  def at(x, y)
    @x, @y = x, y
  end

  def advance
    case bearing
    when :north then @y += 1
    when :east  then @x += 1
    when :south then @y -= 1
    when :west  then @x -= 1
    end
  end

  def coordinates
    [@x, @y]
  end

private

  DIRECTIONS = [:north, :east, :south, :west]

end

class Simulator
  def instructions(letters)
    letters.chars.map { |letter| INSTRUCTIONS[letter] }
  end

  def place(robot, x:, y:, direction:)
    robot.at x, y
    robot.orient direction
  end

  def evaluate(robot, letters)
    instructions(letters).each { |instruction| robot.send(instruction) }
  end

private

  INSTRUCTIONS = { "A" => :advance, 
                   "L" => :turn_left,
                   "R" => :turn_right }
end
