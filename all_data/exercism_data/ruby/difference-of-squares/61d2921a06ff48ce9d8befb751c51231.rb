class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

  def square_of_sums
    sums = n * (n + 1) / 2
    sums * sums
  end

  def sum_of_squares
    terms.inject(0) { |a, e| a + e * e }
  end

  private

  def terms
    (1..n)
  end
end
