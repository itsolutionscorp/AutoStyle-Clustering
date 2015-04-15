class Grains

  def square(n)
    grains = 0
    grains = 2 ** (n - 1)
  end

  def total
    (0..64).map { |number| square(number) }.reduce(0, :+).to_i
  end
end
