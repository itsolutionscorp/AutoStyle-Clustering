class Grains
  def square(which_square)
    2 ** (which_square - 1)
  end

  def total
    (1..64).inject(0) do |sum, index|
      sum += square(index)
    end
  end
end
