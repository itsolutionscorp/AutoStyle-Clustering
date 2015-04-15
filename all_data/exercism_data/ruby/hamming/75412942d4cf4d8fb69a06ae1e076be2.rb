class Hamming
  def self.compute(a, b)
    max = [a, b].max
    [a, b].min.chars.each_with_index.count { |min, i| min != max[i] }
  end
end
