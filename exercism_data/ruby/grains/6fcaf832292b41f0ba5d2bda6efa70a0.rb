require 'pry'

class Grains
  attr_reader :chess_board, :total

  def initialize
    @total = 0
    @chess_board = build_chess_board
  end

  def square(i)
    chess_board[i]
  end

  private

  def first_square
    1
  end

  def last_square
    64
  end

  def increment_total_by(n)
    @total += n
  end

  def build_chess_board
    board = {}

    first_square.upto(last_square) do |square|
      if square > 1
        board[square] = board[square - 1] * 2 
      else
        board[square] = 1
      end

      increment_total_by board[square]
    end

    board
  end
end
