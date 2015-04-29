class Grains
  def square(n)
    2**(n-1)
  end

  def total
    (1..64).inject(0) {|sum, i| sum += square(i)}
  end
end
