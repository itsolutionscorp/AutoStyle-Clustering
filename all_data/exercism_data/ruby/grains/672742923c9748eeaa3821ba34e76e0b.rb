class Grains
  def square(n)
  	2 ** (n - 1)
  end

  def sum_to_square(n)
  	2 ** n - 1
  end

  def total
  	sum_to_square(64)
  end
end
