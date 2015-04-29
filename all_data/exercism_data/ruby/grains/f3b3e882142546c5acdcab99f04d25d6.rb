class Grains
  def board
    unless defined? chess_board
      chess_board = []
      (0..64).inject(1) do |m,i|
        chess_board[i] = m
        m*2
      end
    end
    chess_board
  end

  def square(n)
    board[n-1]
  end

  def total
    board.last - 1
  end
end
