class Hamming
  def self.compute(strand1, strand2)
    shortest = [strand1.length, strand2.length].min
    (0...shortest).count { |i| strand1[i] != strand2[i] }
  end
end
