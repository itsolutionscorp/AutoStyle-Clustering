class Grains

  def square(n)
    2 ** (n - 1)
  end

  def total
    1.upto(64).inject(0) { |total, n| total += square(n) }
  end

end
