# Robot-name exercism

class Robot

  def initialize
    self.reset
  end

  def name
    @robot_name
  end

  def reset
    @robot_name = (0...2).map {(65 + rand(26)).chr}.join + (0...3).map {(48 + rand(9)).chr}.join
  end

end

# robot = Robot.new
# name = robot.name

# puts robot
# puts name

# robot.reset

# name2 = robot.name
# puts robot
# puts name2
