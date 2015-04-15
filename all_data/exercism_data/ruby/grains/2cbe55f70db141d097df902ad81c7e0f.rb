class Grains
  CHESS_BOARD_SQUARES = 64

  def square(position)
    position == 1 ? 1 : 2 * square(position - 1)
  end

  def total(mem=0, position = CHESS_BOARD_SQUARES)
    sum_squares(CHESS_BOARD_SQUARES)
  end

  private

  def sum_squares(position, mem = 0)
    return mem if position == 0

    mem += square(position)
    sum_squares(position - 1, mem)
  end
end
