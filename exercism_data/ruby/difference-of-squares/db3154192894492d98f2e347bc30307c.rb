class Squares
  attr_accessor :num

  def initialize(num)
    @num = num
  end

  def difference
    square_of_nums - sum_of_squares
  end

  def sum_of_squares
    (1..num).inject(0) do |sum, num|
      sum += num**2
      sum
    end
  end

  def square_of_nums
    (1..num).inject(&:+) ** 2
  end
end
