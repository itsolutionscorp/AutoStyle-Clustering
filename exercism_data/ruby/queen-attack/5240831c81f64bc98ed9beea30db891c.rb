class Queens

  attr_reader :white, :black, :board

  def initialize white: [ 0, 3 ], black: [ 7, 3 ]
    @board ||= Board.new white, black
    @white = white
    @black = black
    raise ArgumentError if board.is_not_valid?
  end

  def to_s
    Printer.new( board, white, black ).print 
  end

  def attack?
    board.attack?
  end

end

class Board

  attr_reader :x, :y, :white, :black

  def initialize white, black
    @x = 8
    @y = 8
    @white = white
    @black = black
  end

  def is_not_valid? 
    white.first == black.first and white.last == black.last
  end

  def attack? 
    white.first == black.first or white.last == black.last or  
    white.first + white.last  == black.first + black.last or 
    white.first - black.first == white.last - black.last 
  end

end

class Printer

  BOARD_POINT = '_'
  WHITE_QUEEN = 'W'
  BLACK_QUEEN = 'B'

  attr_reader :board, :white, :black

  def initialize board, white, black
    @board = board
    @white = white
    @black = black
  end

  def print
    board_print = all_board.tap do |square|
      if square[ white.first ][ white.last ] = WHITE_QUEEN
      elsif square[ black.first ][ black.last ] = BLACK_QUEEN
      end
    end
    
    puts board_print
  end

  def all_board
    board.x.times.map { print_row }
  end

  def print_row
    BOARD_POINT * board.y 
  end

end
