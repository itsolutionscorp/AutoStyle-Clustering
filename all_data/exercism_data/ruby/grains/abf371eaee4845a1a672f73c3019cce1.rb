class Grains
  def square(n)
    2**(n-1)
  end

  def total
    (1..64).inject { |sum, x| sum += square(x) }
  end
end