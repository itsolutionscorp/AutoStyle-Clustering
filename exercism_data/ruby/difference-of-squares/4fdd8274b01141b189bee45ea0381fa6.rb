class Squares
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    1.upto(num).inject(:+) ** 2
  end

  def sum_of_squares
    (1..num).to_a.map{|n| n*n}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
