class Squares
  def initialize(number)
    @array = (1..number).to_a
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    @sum_of_squares ||= @array.inject(0) {|sum, x| sum+x**2}
  end

  def square_of_sums
    @square_of_sums ||= @array.inject(:+)**2
  end
end
