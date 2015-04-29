class Robot
  attr_reader :name

  def self.random_name
    name = ''
    name << (65 + rand(26)).chr  # Uppercase Letter
    name << (65 + rand(26)).chr  # Uppercase Letter
    name << rand(10).to_s        # Decimal Digit
    name << rand(10).to_s        # Decimal Digit
    name << rand(10).to_s        # Decimal Digit
  end

  def self.generate_name
    @@robot_name ||= Robot.random_name
    @@first_robot ||= @@robot_name.clone

    # Wrap around the end of the namespace.
    if (@@robot_name == 'ZZ999')
      @@robot_name = 'AA000'
    else
      @@robot_name.next!
    end
    raise Exception.new 'Out of robot names!' if @@robot_name == @@first_robot
    @@robot_name.clone
  end

  def initialize
    @name = Robot.generate_name
  end

  def reset
    @name = Robot.generate_name
  end

end
