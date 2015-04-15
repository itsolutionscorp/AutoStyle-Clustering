class Grains
  CHESS_BOARD_SIZE = 64

  def square(index)
    2 ** (index - 1)
  end

  def total
    2 ** CHESS_BOARD_SIZE - 1
  end
end
