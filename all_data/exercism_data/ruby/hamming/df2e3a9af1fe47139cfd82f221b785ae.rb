class Hamming
  def self.compute(s1, s2)
    s1.length.times.count { |i| s1[i] != s2[i] }
  end
end
