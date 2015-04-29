class Hamming
  def self.compute(strand1, strand2)
    return (0...[strand1.length, strand2.length].min).inject(0){|c, i| strand1[i] == strand2[i] ? c : c + 1}
  end
end
