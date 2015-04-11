class Grains
  CHESSBOARD_SIZE = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    square(CHESSBOARD_SIZE + 1) - 1
  end
end
