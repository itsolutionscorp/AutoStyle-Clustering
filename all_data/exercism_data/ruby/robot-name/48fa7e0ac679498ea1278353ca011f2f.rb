class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = random_name
  end

  def random_name
    random_char * 2 + random_digit.to_s * 3
  end

  def random_char
    ('A'..'Z').to_a.shuffle.first
  end

  def random_digit
    rand(10)
  end
end
