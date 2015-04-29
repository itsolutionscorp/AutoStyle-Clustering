module Hamming
  def self.compute(a, b)
    ## Iterate for the length of the shorter strand
    differences = (0...[a.length, b.length].min).collect { |i| "Difference!" unless a[i] == b[i] }
    differences.count("Difference!")
  end
end
