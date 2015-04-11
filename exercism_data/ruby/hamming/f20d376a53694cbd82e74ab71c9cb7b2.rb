class Hamming
  def self.compute(s1, s2)
    [s1.size, s2.size].min.times.count { |i| s1[i] != s2[i] }
  end
end
