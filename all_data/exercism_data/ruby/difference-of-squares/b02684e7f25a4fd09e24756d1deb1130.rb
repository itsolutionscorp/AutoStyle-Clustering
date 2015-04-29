class Squares
  def initialize(size)
    @size = size
  end

  def square_of_sums
    sqsum = 0
    (1..@size).each do |i|
      sqsum += i
    end
    sqsum**2
  end

  def sum_of_squares
    sumsq = 0
    (1..@size).each do |i|
      sumsq += i**2
    end
    sumsq
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
