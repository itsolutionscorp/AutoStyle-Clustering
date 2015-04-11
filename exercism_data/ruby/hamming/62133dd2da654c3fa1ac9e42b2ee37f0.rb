class Hamming
  def self.compute(a, b)
    num_compare = [a.length, b.length].min
    (0..num_compare-1).count {|i| a[i] != b[i]}
  end
end
