require 'matrix'

class Queens
  attr_reader :white, :black
  def initialize white: [0, 3], black: [7, 3]
    raise ArgumentError if white == black
    @white, @black = white, black
    @board = Array.new(64) { |i| 'O' }
    @board[white.x * 8 + white.y] = 'W'
    @board[black.x * 8 + black.y] = 'B'
  end

  def attack?
    white.x == black.x ||
      white.y == black.y ||
      (white.y - white.x).abs == (black.y - black.x).abs
  end

  def to_s
    @board.map
      .with_index { |e, i| e + (i % 8 == 7 ? "\n" : ' ') }
      .join
      .chomp
  end
end

class Array
  def x; self[0]; end
  def y; self[1]; end
end
