class Grains
  INDEXES = 1..64

  def square(index)
    index = assert_valid(index)
    2**(index-1)
  end

  def total
    2**(INDEXES.last)-1
  end

  def assert_valid(index)
    index = index.to_i
    message = "The index should belong to #{INDEXES}, #{index} given."
    raise ArgumentError, message unless INDEXES === index
    index
  end
end
