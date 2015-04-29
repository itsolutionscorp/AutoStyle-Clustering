class Grains
  def square(n, wheat_on_square=0)
    wheat_on_square == 0 ? wheat_on_square = 1 : wheat_on_square *= 2
    n == 1 ? wheat_on_square : square(n-1, wheat_on_square)
  end

  def total(n=64, sum=0)
    sum += square(n)
    n == 1 ? sum : total(n-1, sum)
  end
end
