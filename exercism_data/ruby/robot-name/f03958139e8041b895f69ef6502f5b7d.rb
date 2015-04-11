class Robot
  attr_accessor :name

  def name
    @name ||= RobotNameGenerator.generate
  end

  def reset
    @name = nil
  end
end

class RobotNameGenerator

  def self.counter
    @@counter ||= "000"
  end

  def self.generate
    (0..1).map{ ('A'..'Z').to_a[rand(26)] }.join + counter.next!
  end
end
