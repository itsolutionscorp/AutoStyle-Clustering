class Squares

  def initialize(x)
    @square = x
  end

  def square_of_sums
    input_val = @square + 1
    squaresum_val = 0
    input_val.times do |i|
      squaresum_val = squaresum_val + i
    end
    squaresum_val = squaresum_val**2
  end

  def sum_of_squares
    input_val = @square +1
    sumsquare_val = 0
    input_val.times do |i|
      sumsquare_val = sumsquare_val + i**2
    end
    sumsquare_val
  end

  def difference
  diff_val = square_of_sums - sum_of_squares
  end
  
end
