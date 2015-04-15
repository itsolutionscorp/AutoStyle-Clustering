class Hamming
  def self.compute(first, second)
    first.split("") \
    .zip(second.split("")) \
    .count{ |x, y| x != y && y!=nil }
  end
end
