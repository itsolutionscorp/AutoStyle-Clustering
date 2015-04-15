class Grains

  def square(n)
    return 1 if n == 1
    square(n - 1) * 2
  end
  
  def total
    (1..64).inject { |sum, n| sum += square(n) }
  end
  
end
