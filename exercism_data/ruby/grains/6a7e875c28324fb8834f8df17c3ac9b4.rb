class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    # Total is one less than the succeeding square
    square(65) - 1
  end
end