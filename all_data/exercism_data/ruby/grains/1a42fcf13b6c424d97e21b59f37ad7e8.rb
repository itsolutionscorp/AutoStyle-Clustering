class Grains
  def square(n)
    2**(n - 1)
  end

  def total
    64.times.map{ |n| square(n + 1) }.inject(:+)
  end
end
