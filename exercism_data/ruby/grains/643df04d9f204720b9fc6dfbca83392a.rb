class Grains

  def square(n)
   return 1 if n == 1
   2 * square(n - 1)
  end

  def total
    all_squares.reduce(:+)
  end

  def all_squares
    (1..64).map{ |n| square(n) }
  end

end
