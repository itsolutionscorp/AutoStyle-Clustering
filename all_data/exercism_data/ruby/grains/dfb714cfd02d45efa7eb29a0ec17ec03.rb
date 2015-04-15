class Grains
  def square(n)
    2**(n-1)
  end

  def total
    (1..64).inject(0) { |result, val| result = result + square(val); result }
  end
end
