class Hamming
  def self.compute(x, y)
    # Hamming distance is undefined for strings of differing lengths.
    return nil unless x.length == y.length
    # Each mismatched character increases the hamming distance.
    x.length.times.count {|n| x[n] != y[n]}
  end
end
