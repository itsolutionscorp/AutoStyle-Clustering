require 'set'

class Robot
  def initialize
    reset
  end

  attr_reader :name

  def reset
    @name = generate_name until TheRobotNameRegistry.register(name)
  end

  def generate_name
    ('A'..'Z').to_a.sample(2).join + "%03d" % Random.rand(1000)
  end
end

class RobotNameRegistry
  def initialize
    @robot_names = Set.new
  end

  attr_reader :robot_names

  def register(robot_name)
    if robot_names.include?(robot_name)
      false
    else
      robot_names << robot_name
      true
    end
  end
end

TheRobotNameRegistry = RobotNameRegistry.new
