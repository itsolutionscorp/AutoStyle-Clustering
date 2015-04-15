class Grains

  def square(square_number)
    2 ** (square_number - 1)
  end

  def total(squares = 64)
    square(squares + 1) - 1
  end

end
