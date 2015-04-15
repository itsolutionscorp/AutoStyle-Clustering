class Grains
  def square(square_position)
    2 ** (square_position - 1)
  end
  
  def total(square_position = 64)
    square(square_position) * 2 - 1
  end
end
