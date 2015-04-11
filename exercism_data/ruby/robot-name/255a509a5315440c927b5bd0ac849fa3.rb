require 'forwardable'

class Robot

  attr_reader :name
  attr_accessor :name_generator
  @robots = []

  def name
    @name ||= set_name
  end

  def reset
    self.class.robots.delete(@name)
    @name = nil
  end

  def self.robots
    @robots
  end

  def name_generator
    @name_generator ||= NameProvider.new
  end

  private

  def set_name
    begin
      @name = name_generator.generate_name
    end while self.class.robots.include?(@name)
    self.class.robots << @name
    @name
  end

end

class NameProvider
  def generate_name
    [letter, letter, number, number, number].join
  end

  def letter
    ("A".."Z").to_a.shuffle
  end

  def number
    ("0".."9").to_a.shuffle
  end

end

# Additional Test to prove duplicates are thrown out
#class RobotTest < MiniTest::Unit::TestCase
#  def test_duplicate_names
#    generator = MiniTest::Mock.new
#    generator.expect(:generate_name, "DW102")
#    generator.expect(:generate_name, "DW102")
#    generator.expect(:generate_name, "DW103")
#    
#    robot = Robot.new
#    robot2 = Robot.new
#    robot.name_generator = generator
#    robot2.name_generator = generator
#
#    assert_equal "DW102", robot.name
#    assert_equal "DW103", robot2.name
#  end
#end
