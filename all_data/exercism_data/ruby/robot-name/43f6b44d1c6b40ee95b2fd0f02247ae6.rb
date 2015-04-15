class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_robot_name
  end

  private

  def generate_robot_name
    random_characters("A", "Z", 2) + random_characters("0", "9", 3)
  end

  def generate_random_character(range)
    range.to_a.sample
  end

  def random_characters(first, last, quantity)
    quantity.times.map {generate_random_character(first..last)}.join
  end
end
