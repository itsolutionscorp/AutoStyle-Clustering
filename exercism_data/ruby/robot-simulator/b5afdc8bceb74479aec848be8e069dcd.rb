class Robot
  attr_reader :bearing, :x, :y

  def valid_directions
    [:north, :south, :east, :west]
  end

  def orient(new_bearing)
    if valid_directions.include?(new_bearing)
      @bearing = new_bearing
    else
      raise ArgumentError.new("#{new_bearing} is not a valid direction")
    end
  end

  def turn_right
    if bearing == :north
      orient(:east)
    elsif bearing == :east
      orient(:south)
    elsif bearing == :south
      orient(:west)
    elsif bearing == :west
      orient(:north)
    else
      raise "Error"
    end
  end

  def turn_left
    if bearing == :north
      orient(:west)
    elsif bearing == :west
      orient(:south)
    elsif bearing == :south
      orient(:east)
    elsif bearing == :east
      orient(:north)
    else
      raise "Error"
    end
  end

  def at(x = 0,y = 0)
    @x = x
    @y = y
  end   

  def coordinates
    [x, y]
  end

  def advance
    if bearing == :north
      @y += 1
    elsif bearing == :south
      @y -= 1
    elsif bearing == :east
      @x += 1
    elsif bearing == :west
      @x -= 1
    end
  end
end

class Simulator

  def instructions(letters)
    letters.chars.collect do |letter|
      command[letter]
    end
  end

  def command
    {"L" => :turn_left, "R" => :turn_right, "A" => :advance}
  end

  def place(robot_name, start_info)
    x_coordinate = start_info[:x]
    y_coordinate = start_info[:y]
    direction = start_info[:direction]
    robot_name.at(x_coordinate, y_coordinate)
    robot_name.orient(direction)
  end

  def evaluate(robot_name, letters)
    orders = instructions(letters)
    orders.each do |order|
      robot_name.send(order)
    end
  end
end
