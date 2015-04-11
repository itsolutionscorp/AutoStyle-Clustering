class Hamming

  def self.compute_dynamic(a, b)
    difference = 0
    (0..([a.size, b.size].min - 1)).each { |i| difference += a[i] == b[i] ? 0 : 1 }
    difference
  end

  def self.compute(a, b)
    (a[0] == nil || b[0] == nil) ? 0 : (a[0] == b[0] ? 0 : 1) + compute(a[1..-1], b[1..-1])
  end

end
