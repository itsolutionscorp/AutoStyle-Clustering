class Grains
  def square(position)
    2**(position-1)
  end

  def total
    chessboard_positions.inject(1) { |sum, n| sum + square(n) } - 1
  end

  private

  def chessboard_positions
    (1..64)
  end
end
