class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = RobotName.new.value
  end
end

class RobotName
  attr_reader :value

  def initialize
    @value = random_letter +
             random_letter +
             random_digit +
             random_digit +
             random_digit
  end

  private

  def random_digit
    rand(10).to_s
  end

  def random_letter
    rand(26).to_s(26)
  end
end
