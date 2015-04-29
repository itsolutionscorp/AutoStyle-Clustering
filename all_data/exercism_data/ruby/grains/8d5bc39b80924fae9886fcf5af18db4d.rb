class Grains
  def square(square_number)
    2 ** (square_number - 1)
  end

  def total
    (1..64).reduce{|sum, square_number| sum + square(square_number)}
  end
end
