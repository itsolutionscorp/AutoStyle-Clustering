class Grains
  attr_accessor :square

  def square(n)
    2 ** (n - 1)
  end

  def total
    square(64) * 2 - 1
  end
end
