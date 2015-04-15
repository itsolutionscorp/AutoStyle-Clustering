class Grains
  def board
    chess_board = [1]
    (2..64).each_with_index do |n,i|
      chess_board << chess_board[i]*2
    end
    chess_board
  end

  def square(n)
    board[n-1]
  end

  def total
    board.inject(:+)
  end
end
