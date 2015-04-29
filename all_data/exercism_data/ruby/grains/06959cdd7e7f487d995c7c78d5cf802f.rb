class Grains
  # Optimized for readability, not speed.
  # http://en.wikipedia.org/wiki/Wheat_and_chessboard_problem#Solutions

  CHESS_BOARD_SQUARES = 64

  def square(number)
    if number == 1
      1
    else
      2 * square(number - 1)
    end
  end

  def total
    1.upto(number_of_squares).inject(0) { |total, num| total + square(num) }
  end

  private

  def number_of_squares
    CHESS_BOARD_SQUARES
  end
end
