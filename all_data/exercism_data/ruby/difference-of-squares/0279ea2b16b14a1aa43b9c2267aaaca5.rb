class Squares
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = one_to_num.inject(0) { |result, num| result + num }
    sum**2
  end

  def sum_of_squares
    one_to_num.inject(0) { |result, num| result + num**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def one_to_num
    (1..num).to_a
  end
end
