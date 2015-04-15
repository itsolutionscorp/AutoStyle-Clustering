# Grains class.
class Grains
  NUM_SQUARES = 64

  def initialize
    @grains = Array.new(NUM_SQUARES)
  end

  def square(num)
    @grains[num - 1] ||= calculate_grains(num)
  end

  def total
    (1..NUM_SQUARES).to_a.inject(0) { |a, e| a + square(e) }
  end

  def calculate_grains(num)
    raise ArgumentError if (num == 0) || (num > NUM_SQUARES)
    2**(num - 1)
  end

  private :calculate_grains
end
