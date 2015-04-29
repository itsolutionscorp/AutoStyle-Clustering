class Grains
  def square(n)
    2 ** (n-1)
  end

  def total
    (0..63).reduce(0) {|total, n| total + 2**n}
  end
end
