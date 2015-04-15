class Robot

  def initialize
    @name = ''
  end

  def new
    new_robot = Robot.new
    new_robot.name = Robot.name
  end


  def name
    robot_name = ''
    x = (0..1).map { ('A'..'Z').to_a[rand(26)] }.join
    robot_name << x
    y = (0..2).map { (0..9).to_a[rand(10)] }.join
    robot_name << y
  end

  def reset
    @name = nil
  end

end
