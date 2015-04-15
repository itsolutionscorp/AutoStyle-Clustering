class Squares
  attr_accessor :num

  def initialize(num)
    self.num = num
  end

  def square_of_sums
    (0..num).to_a.inject(0) { |prev, i| prev + i } ** 2
  end

  def sum_of_squares
    (0..num).to_a.inject(0) { |prev, i| prev + (i ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
