class Grains
  CHESS_BOARD_SQUARES = (1..64)

  def square(index)
    2 ** (index - 1)
  end

  def total
    CHESS_BOARD_SQUARES.reduce {|sum, index| sum += square(index)}
  end
end
