class Robot

  attr_accessor :bearing, :coordinates

  def orient(direction)
    raise ArgumentError unless [:east, :west, :north, :south].include? direction.to_sym
    self.bearing = direction
  end

  def turn_right
    case self.bearing
    when :east
      self.bearing = :south
    when :west
      self.bearing = :north
    when :north
      self.bearing = :east
    when :south
      self.bearing = :west
    end
  end

  def turn_left
    case self.bearing
    when :east
      self.bearing = :north
    when :west
      self.bearing = :south
    when :north
      self.bearing = :west
    when :south
      self.bearing = :east
    end
  end

  def advance
    case self.bearing
    when :east
      self.coordinates[0] += 1
    when :west
      self.coordinates[0] -= 1
    when :north
      self.coordinates[1] += 1
    when :south
      self.coordinates[1] -= 1
    end
  end

  def at(x,y)
    self.coordinates = [x,y]
  end


end

class Simulator

  def instructions(ins_string)
    commands  =[]
    ins_string.split("").each do |i|
      case i
      when "L"
        commands << :turn_left
      when "R"
        commands << :turn_right
      when "A"
        commands << :advance
      end
    end
    commands
  end
  
  def place(robot, x: 0, y: 0, direction: :east)
    robot.at(x,y)
    robot.orient(direction)
  end
  
  def evaluate(robot,ins_string)
    instructions(ins_string).each do |instruction|
      robot.send instruction
    end    
  end

end
