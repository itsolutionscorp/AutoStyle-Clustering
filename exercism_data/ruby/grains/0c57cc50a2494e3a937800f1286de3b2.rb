class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    (2 ** total_number_of_squares) - 1
  end

  private

  def total_number_of_squares
    64
  end
end
