class Grains
  CHESS_BOARD_SQUARES = 64

  def square(position)
    position == 1 ? 1 : 2 * square(position - 1)
  end

  def total
    (1..CHESS_BOARD_SQUARES).to_a.map { |position| square(position) }.inject(:+)
  end
end
