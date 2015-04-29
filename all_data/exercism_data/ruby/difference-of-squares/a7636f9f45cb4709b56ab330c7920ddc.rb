class Squares
  def initialize(size)
    @size = size
  end

  def square_of_sums
    sum = 0
    1.upto(@size) do |n|
      sum += n
    end
    sum ** 2

  end

  def sum_of_squares
    squares = 0
    1.upto(@size) do |n|
      squares += n ** 2
    end
    squares
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
