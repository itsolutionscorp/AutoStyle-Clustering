class Grains
  Squares = (2..64).reduce([1]) { |memo,_| memo << memo.last*2 }

  def square num
    Squares[num - 1]
  end

  def total
    Squares.inject :+
  end
end
