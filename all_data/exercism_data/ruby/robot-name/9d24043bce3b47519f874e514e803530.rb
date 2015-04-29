class Robot
  attr_reader :name
  @@robot_name = 'AA000'

  def self.generate_name
    @@robot_name.next!.clone
  end

  def initialize
    @name = Robot.generate_name
  end

  def reset
    @name = Robot.generate_name
  end

end
