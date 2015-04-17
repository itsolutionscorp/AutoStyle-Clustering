class Grains

  def square(square_number)
    2 ** (square_number - 1)
  end

  def total
    (0..64).map { |number| square(number) }.reduce(0, :+).to_i
  end

end