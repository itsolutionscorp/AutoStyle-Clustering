class Grains

  def square(placement)
    2 ** (placement - 1)
  end

  def total
    (0..64).reduce { |sum, number|  sum + square(number)}
  end

end
