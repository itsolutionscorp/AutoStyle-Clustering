class Squares 

  def initialize n
    @n = n
  end

  def square_of_sums
    sums = 0
    @n.times do |i|
      sums = sums + i +1
    end
    sums ** 2
  end

  def sum_of_squares
    sums = 0
    @n.times do |i|
      sums = sums + (i+1)**2
    end
    sums
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
