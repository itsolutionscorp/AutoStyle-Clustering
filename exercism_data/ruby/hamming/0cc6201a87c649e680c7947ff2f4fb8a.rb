class Hamming
  def self.compute s1, s2
    length = [s1.length, s2.length].min

    length.times.count { |i| s1[i] != s2[i] }
  end
end
