class Grains
  def square(number_of_squares)
    2 ** (number_of_squares - 1)
  end

  def total
    total = 0
    n = 0
    while n < 64 do
      total += 2 ** n
      n += 1
    end
    total
  end

end
