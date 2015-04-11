class Grains
  def square(n)
    return 2 ** (n - 1)
  end

  def total
    return square(65) - 1
  end
end
