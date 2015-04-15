class Grains
  def square(square_number)
    2 ** (square_number - 1)
  end
  def total
    n = 0
    until n == 65
    total_grains = 2 ** n - 1
    n += 1
    end
  total_grains
  end
end
