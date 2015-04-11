class Hamming
  def self.compute(first, second)
    compare(first.each_char.to_a, second.each_char.to_a)
  end

  def self.compare(a, b)
    a.each_with_index.map do |a, i|
      true if a != b[i]
    end.compact.count
  end
end
