class Robot

# class methods & initializations
  class << self
    attr_accessor :roster
  end
  self.roster = []
  ALPHA_CNT = 2
  NUM_CNT   = 3

  # instance methods & initialization 
  def initialize
    @name = nil
  end

  def name
    @name ||= unique_random_name
  end

  def reset
    remove_from_roster
    @name = nil
  end

private
  def unique_random_name
    rn = random_name
    while Robot.roster.include? rn
      rn = random_name
    end
    Robot.roster << rn
    rn
  end

  def remove_from_roster
    Robot.roster.reject! {|rn| @name == rn }
  end

  def random_name
    rn = ''
    ALPHA_CNT.times {rn << random_UC_char }
    puts NUM_CNT.times {rn << random_digit }
    rn
  end

  def random_digit
    [*('0'..'9')].sample(1).first
  end

  def random_UC_char
    [*('A'..'Z')].sample(1).first
  end
end


class RobotTest < MiniTest::Test
  def setup
    Robot.roster.clear
  end

  def test_adds_robot_name_to_roster
    robot = Robot.new
    name = robot.name
    assert_equal [name], Robot.roster
  end

  def test_unique_names_guarantee
    robot1 = Robot.new
    def robot1.random_name; "QR000"; end #stub
    robot1.name

    robot2 = Robot.new
    # supply 'QR000' then QR001 for random_name
    def robot2.random_name
      @counter ? @counter += 1 : @counter = 0
      "QR00"+['0','1'][@counter]
    end #stub
    robot2.name

    assert_equal ["QR000", "QR001"], Robot.roster
  end

  def test_name_removed_from_roster_on_reset
    robot = Robot.new
    robot.name
    robot.reset
    assert_equal [], Robot.roster
  end
end
