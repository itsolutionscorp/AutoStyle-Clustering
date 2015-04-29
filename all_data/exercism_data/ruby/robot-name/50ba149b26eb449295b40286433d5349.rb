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

  def random_name
    rn = ''
    ALPHA_CNT.times {rn << random_UC_char }
    NUM_CNT.times {rn << random_digit }
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

  def test_unique_names_guaranteed
    assert_equal [], Robot.roster

    robot1 = Robot.new
    def robot1.random_name; "AZ123"; end #stub
    name = robot1.name
    assert_equal ["AZ123"], Robot.roster
    assert_equal "AZ123", name

    robot2 = Robot.new
    def robot2.random_name; "QR000"; end #stub
    name = robot2.name
    assert_equal ["AZ123", "QR000"], Robot.roster
    assert_equal "QR000", name

    robot3 = Robot.new
    def robot3.random_name
      @counter ? @counter += 1 : @counter = 0
      "QR00"+['0','1','2'][@counter]
    end #stub
    name = robot3.name
    assert_equal ["AZ123", "QR000", "QR001"], Robot.roster
    assert_equal "QR001", name
  end
end
