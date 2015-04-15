class Grains
  def initialize
    @squares = Array.new(64)
  end

  def square(n)
    @squares[n] ||= 2**(n - 1)
  end

  def total
    @total ||= (1..64).map { |x| square x }.inject(:+)
  end
end
