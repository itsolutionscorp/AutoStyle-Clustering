class Grains
  CHESS_BOARD_SQUARES = 64

  def square(position)
    2 ** (position - 1)
  end

  def total
    (2 ** CHESS_BOARD_SQUARES) -1
  end
end
