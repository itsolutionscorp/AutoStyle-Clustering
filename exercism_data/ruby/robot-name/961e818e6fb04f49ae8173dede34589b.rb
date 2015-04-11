class Robot
  attr_accessor :name, :name_generator

  def initialize
    self.name_generator = RobotNameGenerator.new
  end

  def name
    @name ||= name_generator.generate
  end

  def reset
    @name = nil
  end
end

class RobotNameGenerator
  attr_accessor :counter

  def initialize
    self.counter = "000"
  end

  def generate
    (0..1).map{ ('A'..'Z').to_a[rand(26)] }.join + counter.next!
  end
end
