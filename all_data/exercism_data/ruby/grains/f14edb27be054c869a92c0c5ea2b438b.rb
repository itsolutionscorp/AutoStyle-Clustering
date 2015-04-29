class Grains
  CHESS_BOARD_SQUARES = 64

  def square(position)
    position == 1 ? 1 : 2 * square(position - 1)
  end

  def total
    (1..CHESS_BOARD_SQUARES).reduce(0) { |total, position| total + square(position) }
  end
end
