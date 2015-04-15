class Grains

  def square(n)
    two_to_the_power_of(n - 1)
  end

  def total
    (1..64).reduce(0) { |sum, n| sum + square(n) }
  end

  private

  def two_to_the_power_of(n)
    1 << n
  end

end
