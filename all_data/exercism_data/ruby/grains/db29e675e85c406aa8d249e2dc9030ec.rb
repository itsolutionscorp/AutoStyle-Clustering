class Grains

  def square(index)
    return 1 if index == 1
    2 ** (index - 1)
  end

  def total
    (1..64).map { |index| square(index) }.reduce(0, :+)
  end

end
