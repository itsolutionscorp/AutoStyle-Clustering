class Grains
  CHESS_BOARD_SQUARES = 64

  def square(square_number)
    2 ** square_number - 2 ** (square_number - 1)
  end

  def total
    2 ** CHESS_BOARD_SQUARES - 1
  end
end
