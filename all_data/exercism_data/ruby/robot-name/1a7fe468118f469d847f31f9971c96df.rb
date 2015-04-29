ROBOT_LIBRARY = []

class Robot
  attr_accessor :robot_name, :name_to_test

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
    @name_to_test = ('A'..'Z').to_a.sample(2).join('').concat((0..9).to_a.sample(3).join('').to_s)
  end

  def set_name
    generate_name
    until !ROBOT_LIBRARY.include? @name_to_test
      generate_name
    end
    @robot_name = @name_to_test
    ROBOT_LIBRARY << @robot_name
  end
end
