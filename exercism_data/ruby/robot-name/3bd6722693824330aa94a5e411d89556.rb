require 'set'

ROBOT_NAMES_IN_USE = Set.new

class Robot
  def initialize name_generator = NameGenerator.new, names_in_use = ROBOT_NAMES_IN_USE
    @name_generator = name_generator
    @names_in_use = names_in_use
    assign_new_name
  end

  attr_reader :name

  def reset
    @names_in_use.delete @name
    assign_new_name
  end

  private  

  def assign_new_name
    begin
      new_name = @name_generator.generate
    end until !@names_in_use.include? new_name

    @names_in_use.add new_name
    @name = new_name
  end
end

class NameGenerator
  LETTERS = ('A'..'Z').to_a
  DIGITS  = ('0'..'9').to_a

  def generate
    (LETTERS.sample(2) + DIGITS.sample(3)).join
  end
end

=begin
Additional tests:
-----------------

  def test_name_already_in_use_will_not_be_used
    generator = FakeNameGenerator.new ['X', 'X', 'Y']
    names_in_use = Set.new

    robot1 = Robot.new(generator, names_in_use)
    robot2 = Robot.new(generator, names_in_use)

    assert_equal 'Y', robot2.name
  end

  def test_after_reset_names_are_reused
    generator = FakeNameGenerator.new ['X', 'Y', 'X']

    names_in_use = Set.new

    robot1 = Robot.new(generator, names_in_use)
    robot1.reset
    robot2 = Robot.new(generator, names_in_use)

    assert_equal 'X', robot2.name
  end

  class FakeNameGenerator
    def initialize name_array
      @name_array = name_array
    end
    def generate
      @name_array.shift    
    end
  end
=end
