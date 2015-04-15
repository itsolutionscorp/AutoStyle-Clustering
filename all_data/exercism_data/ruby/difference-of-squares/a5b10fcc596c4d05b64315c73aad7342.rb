class Squares
  attr_reader :arr

  def initialize(n)
    @arr = (1..n).to_a
  end

  def square_of_sums
    arr.inject(:+) ** 2
  end

  def sum_of_squares
    arr.inject(0) do |sum, i|
      sum += i**2
    end
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
