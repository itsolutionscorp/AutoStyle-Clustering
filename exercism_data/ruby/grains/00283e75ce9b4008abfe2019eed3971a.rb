class Grains

  MAX_SQUARES = 64

  def square number
   2 ** number.pred
  end

  def total
    square( MAX_SQUARES.next ) - 1
  end

end
