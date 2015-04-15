class Grains
  def square(n)
    2 ** (n-1)
  end

  def total
    (1..64).to_a.map{ |n| square(n) }.reduce(:+)
  end
end
