class Grains
  CHESSBOARD_SIZE = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    (1..CHESSBOARD_SIZE).map { |s| square(s) }.inject(:+)
  end
end
