class Grains
  NUMBER_OF_SQUARES = 64
  def square(square_index)
    2 ** (square_index-1)
  end

  def total
    (1..NUMBER_OF_SQUARES).reduce(0) do |sum, index|
      sum + square(index)
    end
  end

end
