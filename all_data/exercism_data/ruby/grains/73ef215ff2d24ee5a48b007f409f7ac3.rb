class Grains
  # http://en.wikipedia.org/wiki/Wheat_and_chessboard_problem#Solutions

  CHESS_BOARD_SQUARES = 64

  def initialize(number_of_squares = CHESS_BOARD_SQUARES)
    @number_of_squares = number_of_squares
  end

  def square(number)
    2**(number-1)
  end

  def total
    # About twice as fast as naively summing all squares.
    square(@number_of_squares + 1) - 1
  end
end
