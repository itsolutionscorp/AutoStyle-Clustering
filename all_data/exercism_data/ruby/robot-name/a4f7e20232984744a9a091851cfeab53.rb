class Robot
  def initialize
    RobotRegistry.register(self)
  end

  def name
    RobotRegistry.robot_name(self)
  end

  def reset
    RobotRegistry.reregister(self)
  end
end

class RobotRegistry
  def self.reregister(robot)
    unregister(robot)
    register(robot)
  end

  def self.unregister(robot)
    names.delete(robot)
  end

  def self.register(robot)
    names[robot] = unique_name
  end

  def self.robot_name(robot)
    names[robot]
  end

  class << self
    private

    def unique_name
      name = ''
      loop do
        break unless names.values.include?(name = RobotName.generate)
      end
      name
    end

    def names
      @names ||= {}
    end
  end
end

class RobotName
  def self.generate
    name = ''
    2.times { name << rand_letter }
    3.times { name << rand_digit }
    name
  end

  class << self
    private

    def rand_letter
      rand_from_range(('A'..'Z'))
    end

    def rand_digit
      rand_from_range(('0'..'9'))
    end

    def rand_from_range(range)
      range.to_a[rand(range.count)]
    end
  end
end
