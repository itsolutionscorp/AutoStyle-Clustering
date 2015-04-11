class Grains
  def square n
    1 if n == 1
    2**(n-1)
  end

  def total
    arg = (1..64).to_a
    arg.inject(0){ |a, i| a + square(i) }
  end
end
