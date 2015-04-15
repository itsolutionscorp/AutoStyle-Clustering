require 'set'
ROBOT_LIBRARY = Set.new [nil]

class Robot
  attr_accessor :robot_name

  def initialize
    set_name
  end

  def name
    @robot_name
  end

  def reset
    set_name
  end

  private

  def generate_name
    ('A'..'Z').to_a.sample(2).join('').concat((0..9).to_a.sample(3).join('').to_s)
  end

  def set_name
    @robot_name = generate_name until !ROBOT_LIBRARY.include?(@robot_name)
    ROBOT_LIBRARY << @robot_name
  end
end
