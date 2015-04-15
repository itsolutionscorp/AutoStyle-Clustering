class Queens

  attr_reader :board

  def initialize white: [ 0, 3 ], black: [ 7, 3 ]
    @board = Board.new white, black

    raise ArgumentError if board.is_not_valid?
  end

  def to_s
    Printer.new( board ).print
  end

  def attack?
    board.attack?
  end

  def white 
    board.white
  end

  def black
    board.black
  end

end

class Board

  attr_reader :size_x, :size_y, :white, :black

  def initialize white, black, size_x = 8, size_y = 8
    @size_x = size_x
    @size_y = size_y
    @white  = white
    @black  = black
  end

  def is_not_valid?
    white == black
  end

  def attack?
    vertical_attack? or
    horizontal_attack? or
    first_diagonal_attack? or
    second_diagonal_attack?
  end

private

  def horizontal_attack?
    white.last == black.last
  end

  def vertical_attack?
    white.first == black.first
  end

  def first_diagonal_attack?
    white.first + white.last == black.first + black.last
  end

  def second_diagonal_attack?
    white.first - black.first == white.last - black.last
  end

end

class Printer

  BOARD_POINT = '_'
  WHITE_QUEEN = 'W'
  BLACK_QUEEN = 'B'

  attr_reader :board

  def initialize board
    @board = board
  end

  def print
    puts board_with_queens
  end

private

  def board_with_queens
    board_array.tap do |board|
      board[ white.first ][ white.last ] = WHITE_QUEEN
      board[ black.first ][ black.last ] = BLACK_QUEEN
    end
  end

  def board_array
    [ row ] * board.size_x
  end

  def row
    BOARD_POINT * board.size_y
  end

  def white
    board.white
  end

  def black
    board.black
  end

end
