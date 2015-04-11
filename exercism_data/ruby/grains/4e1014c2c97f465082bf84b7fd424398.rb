class Grains
  CHESS_BOARD_SQUARES = 64

  def square(number)
    2**number.pred
  end

  def total
    1.upto(CHESS_BOARD_SQUARES).inject { |total, num| total + square(num) }
  end
end
