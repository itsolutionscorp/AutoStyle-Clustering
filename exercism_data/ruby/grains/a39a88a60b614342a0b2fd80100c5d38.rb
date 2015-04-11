class Grains
  def square(n)
    2 ** (n-1)
  end

  def total
    # (1..64).reduce { |sum, i| sum += square(i) }

    # OR

    2 ** 64 - 1
  end
end
