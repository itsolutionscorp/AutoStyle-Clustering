class Grains

  NUMBER_SQUARES = 64

  def square(square_number)
    2**(square_number - 1)
  end

  def total
    # (1..64).reduce(0) {|sum, square_number| sum + square(square_number)}
    square(NUMBER_SQUARES + 1) - 1
  end
end
