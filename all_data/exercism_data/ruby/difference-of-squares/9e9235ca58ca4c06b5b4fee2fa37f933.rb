class Squares
  def initialize value
    @range = 1..value
  end

  def square_of_sums
    @range.inject(0) {|sum, i| sum + i } ** 2
  end

  def sum_of_squares
    @range.inject(0) {|sum, i| sum + i**2 }
  end

  def difference 
    (sum_of_squares - square_of_sums).abs
  end
end
