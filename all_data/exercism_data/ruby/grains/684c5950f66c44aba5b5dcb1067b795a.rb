class Grains
  def square(position)
    2 ** (position - 1)
  end

  def total
    (1..64)
      .lazy
      .map { |position| square(position) }
      .reduce(0) { |total, grains| total += grains }
  end
end
