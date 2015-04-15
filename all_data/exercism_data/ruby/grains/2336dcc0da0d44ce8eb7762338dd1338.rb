class Grains
  def square(index)
    return index if index == 1
    2 * square(index.pred)
  end

  def total
    (1..64).map {|index| square index }.inject &:+
  end
end
