class Grains
  def initialize
    @squares = Hash.new { |h,k| h[k] = 2 ** (k-1) }
  end

  def square(n)
    @squares[n]
  end

  def total
    @squares.values_at(*1..64).reduce(&:+)
  end
end
