class Grains
  def square(n)
    raise ArgumentError if n < 0

    2**(n - 1)
  end

  def total
    (1..64).map { |n| square(n) }.inject(0, :+)
  end
end
