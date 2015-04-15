class Robot
  attr_accessor :name
  
  def name
    @name || reset
  end
  
  def reset
    @name = RobotName.generate
  end
end

class RobotName
  def self.generate
    [*'A'..'Z'].sample(2).join + [*0..9].sample(3).join
  end
end
