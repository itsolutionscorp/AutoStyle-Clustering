class Grains
  def square(n)
    grains = 2**(n-1)
  end
  def total
    total = 0
    (1..64).each do |i|
      total += square(i)
    end
    total
  end
end
