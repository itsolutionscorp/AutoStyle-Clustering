class Grains
  # build gameboard once, and let #square method retrieve
  # grains from array rather than calculating grains for each call.
  def board
    @board ||= (2..64).inject([1]) { |board| board << board.last * 2 }
  end
 
  def square(num)
    board[num-1]
  end

  def total
    board.inject(:+)
  end
end
