class Grains
  # http://en.wikipedia.org/wiki/Wheat_and_chessboard_problem#Solutions

  CHESS_BOARD_SQUARES = 64

  def initialize(squares = CHESS_BOARD_SQUARES)
    @squares = squares
  end

  def square(number)
    2**(number-1)
  end

  def total
    # About twice as fast as naively summing all squares.
    square(squares + 1) - 1
  end

  private

  def squares
    @squares
  end
end
