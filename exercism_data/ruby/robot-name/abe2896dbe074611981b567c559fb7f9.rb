class Robot
  attr_reader :name

  def initialize
    @name = random_name
  end

  def ==(other_robot)
    self.name == other_robot.name
  end

  def reset
    @name = random_name
  end

  private

  def random_name
    random_letter + random_letter + random_digit + random_digit + random_digit
  end

  def random_letter
    ('a'..'z').to_a[rand(26)]
  end

  def random_digit
    rand(10).to_s
  end
end
