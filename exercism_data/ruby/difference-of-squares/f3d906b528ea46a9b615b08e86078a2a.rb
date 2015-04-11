class Squares
  attr_reader :num

  def initialize(n)
    @num = n
  end

  def square_of_sums
    @square_of_sums ||= 1.upto(num).inject do |sum, item|
      sum += item
    end**2
  end

  def sum_of_squares
    @sum_of_squares ||= 1.upto(num).inject do |sum, item|
      sum += item**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
