class Grains

  def square(nth_square)
    if nth_square <= 64
      2**(nth_square-1)
    end
  end

  def total
    2**64 - 1
  end

end
