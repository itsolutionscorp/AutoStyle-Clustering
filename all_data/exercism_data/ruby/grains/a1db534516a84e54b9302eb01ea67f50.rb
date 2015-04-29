class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total
    64.times.inject(0) { |sum, i| sum += square(i + 1) }
  end
end
