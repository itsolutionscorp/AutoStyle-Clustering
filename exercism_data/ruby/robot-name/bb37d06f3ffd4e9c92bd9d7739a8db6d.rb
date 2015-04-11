class Robot
  attr_accessor :name
  def initialize
    @name = set_name
  end

  def reset
    set_name
  end

  def set_name
    @name = two_random_letters + three_random_numbers
  end

  def letters
    [('a'..'z').to_a, ('A'..'Z').to_a].flatten
  end

  def two_random_letters
    letters.shuffle.slice(-2, 2).join
  end

  def numbers
    numbers = (0..9).to_a
  end

  def three_random_numbers
    numbers.shuffle.slice(-3, 3).join
  end
end
