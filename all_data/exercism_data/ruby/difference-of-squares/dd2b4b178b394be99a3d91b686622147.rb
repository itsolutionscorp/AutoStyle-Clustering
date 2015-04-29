class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    sums = (1..@n).inject(0) {|sum,x| sum+x}
    sums**2
  end

  def sum_of_squares
    squared_array = (1..@n).collect { |n| n**2 }
    squared_array.inject(0) {|sum,x| sum+x}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
