# robot.rb
class Robot
  attr_reader :name

  class << self
    def names
      @names ||= generate_names
    end

    # storing 676k names in memory inefficient? nah
    def generate_names
      alpha = ('A'..'Z').to_a
      numeric = (0..9).to_a
      head, *tail = [alpha, alpha, numeric, numeric, numeric]
      head.product(*tail).map(&:join).shuffle
    end

    def reset_names
      @names = nil
    end
  end

  def initialize
    dub
  end

  def dub
    @name = Robot.names.pop || 'NAMELESS'
  end

  def reset
    dub
  end
end

# Additional test for unique names:
#
# def test_should_not_repeat_names
#   Robot.reset_names
#   robot = Robot.new
#   676000.times do
#     new_robot = Robot.new
#     assert robot.name != new_robot.name
#     robot = new_robot
#   end
#   assert_equal 'NAMELESS', Robot.new.name
#   Robot.reset_names
# end
