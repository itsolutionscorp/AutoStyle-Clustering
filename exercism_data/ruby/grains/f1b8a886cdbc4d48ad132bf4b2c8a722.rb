class Grains
  def square(square_number)
    1 << square_number - 1
  end

  def total
    square(65) - 1
  end
end
