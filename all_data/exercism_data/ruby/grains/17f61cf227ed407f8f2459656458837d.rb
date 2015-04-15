class Grains

  def square(i)
    2 **(i - 1)
  end

  def total
    (1..64).inject(0) { |sum, i| sum + square(i)}
  end
end
