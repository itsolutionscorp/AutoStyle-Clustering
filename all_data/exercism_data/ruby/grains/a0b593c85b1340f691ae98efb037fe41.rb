class Grains
  def square(number_of_squares)
    2 ** (number_of_squares-1)
  end

  def total
    square(65) - 1
  end
end
