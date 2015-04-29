class Squares

  attr_reader :num

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    1.upto(num).inject(0) do |sum, i|
      sum += (i ** 2)
    end
  end

  def square_of_sums
    sum = 1.upto(num).inject(0) do |sum, i|
      sum += i
    end
    sum ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
