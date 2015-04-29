class Grains
  def square(number)
    2**(number-1)
  end

  def total
    chessboard_squares = 64
     (2**chessboard_squares)-1
  end

end
