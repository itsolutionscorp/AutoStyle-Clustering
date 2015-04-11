class Simulator
  COMMAND_MAP = {
    'L'=>:turn_left,
    'R'=>:turn_right,
    'A'=>:advance
  }
  def instructions commands
    commands.chars.map{|id| COMMAND_MAP[id] || :noop}
  end
  
  def place robot, x: 0, y: 0, direction: :north
    robot.at(x,y).orient(direction)
  end
  
  def evaluate robot, commands
    instructions(commands).each{|command| robot.send command }
  end
end

class Robot
  BEARINGS = %i(north east south west)
  attr_reader :bearing,:coordinates
  def orient direction
    fail ArgumentError unless valid_bearing? direction
    @bearing = direction
    self
  end
  
  def at x,y
    @coordinates = [x,y]
    self
  end
  
  def advance
    case bearing
    when :north
      @coordinates[1]+=1
    when :east
      @coordinates[0]+=1
    when :south
      @coordinates[1]-=1
    when :west
      @coordinates[0]-=1
    end
    self
  end
  
  def turn_left
    @bearing = bearing_left
    self
  end
  
  def turn_right
    @bearing = bearing_right
    self
  end
  
  def noop
  end
  
  private
  
  def bearing_left
    BEARINGS[(bearing_index-1) % 4]
  end
  
  def bearing_right
    BEARINGS[(bearing_index+1) % 4]
  end
  
  def bearing_index
    BEARINGS.index(bearing)
  end
  
  def valid_bearing? direction
    BEARINGS.include? direction
  end
end
