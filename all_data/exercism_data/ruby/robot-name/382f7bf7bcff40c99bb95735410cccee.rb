class Robot
  attr_reader :name, :name_generator

  @robots = []

  def initialize(name_generator = NameGenerator.new)
    @name_generator = name_generator
    reset
  end

  def reset
    self.class.robots.delete(@name)
    begin
      @name = name_generator.generate
    end while self.class.robots.include?(@name)
    self.class.robots << @name
  end

  def self.robots
    @robots
  end
end

class NameGenerator
  def generate
    [letter, letter, number, number, number].join
  end

  def letter
    ("A".."Z").to_a.shuffle
  end

  def number
    ("0".."9").to_a.shuffle
  end
end

# Additional test to test duplicate names do are not allowed
#def test_duplicate_names
#  generator = MiniTest::Mock.new
#  generator.expect(:generate, "DW102")
#  generator.expect(:generate, "DW102")
#  generator.expect(:generate, "DW103")
#
#  robot = Robot.new(generator)
#  robot2 = Robot.new(generator)
#
#  assert_equal "DW102", robot.name
#  assert_equal "DW103", robot2.name
