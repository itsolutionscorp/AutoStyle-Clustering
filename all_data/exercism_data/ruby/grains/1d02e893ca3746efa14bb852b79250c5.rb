class Grains
  def square(n)
    squares[n - 1]
  end

  def total
    @total ||= squares.inject(:+)
  end

  def squares
    @squares ||= (1..64).map { |x| 2**(x - 1) }
  end
end
