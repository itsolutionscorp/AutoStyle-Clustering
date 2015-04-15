class Hamming
  def self.compute(strand1, strand2)
    max = [ strand1.size, strand2.size ].min
    
    distance = (0...max).count { |i| strand1[i] != strand2[i] }
  end
end
