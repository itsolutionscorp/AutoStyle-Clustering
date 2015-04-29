class Grains
  def initialize
    @chessboard_fields = 64
  end

  def square(n)
    2**(n-1)
  end

  def total
    square(@chessboard_fields + 1) - 1
  end
end
