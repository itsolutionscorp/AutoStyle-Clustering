class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    result = (1..@num).inject(:+)
    result**2
  end

  def sum_of_squares
    (1..@num).inject(0) do |result, element|
      result += element**2
      result
    end
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
