class Robot
  DIRECTIONS = [:north, :east, :south, :west]
  MOVES = [[0, 1], [1, 0], [0, -1], [-1, 0]]

  def at(x, y)
    @coords = [x, y]
  end

  def coordinates
    @coords
  end
  
  def orient(direction)
    raise ArgumentError unless DIRECTIONS.include?(direction)
    @bearing = DIRECTIONS.index(direction)
  end
  
  def bearing
    DIRECTIONS[@bearing]
  end
  
  def turn_right
    @bearing = cycle(@bearing + 1)
  end
  
  def turn_left
    @bearing = cycle(@bearing - 1)
  end
  
  def advance
    @coords = [@coords, MOVES[@bearing]].transpose.map do |move|
      move.reduce(:+)
    end
  end
  
private
  def cycle(num)
    num % DIRECTIONS.length
  end
end

class Simulator
  INSTRUCTIONS = {  'L' => :turn_left,
                    'R' => :turn_right,
                    'A' => :advance }
                    
  def instructions(literal)
    literal.chars.map { |char| INSTRUCTIONS[char] }
  end
  
  def place(robot, options = {})
    robot.at(options[:x] || 0, options[:y] || 0)
    robot.orient(options[:direction] || :north)
  end
  
  def evaluate(robot, commands)
    instructions(commands).each { |command| robot.send(command) }
  end
end
