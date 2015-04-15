class Grains

  CHESSBOARD_SIZE = 64

  def square number
    2 ** number.pred
  end

  def total
    square( CHESSBOARD_SIZE.next ) - 1
  end

end
