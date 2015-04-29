class Hamming
  def self.compute(a, b)
    size = [ a.size, b.size ].min
    count = 0
    0.upto(size - 1) { |i| count += 1 if a[i] != b[i] }
    count
  end
end
