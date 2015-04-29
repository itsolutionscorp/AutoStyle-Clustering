class Hamming
  def self.compute(strand1, strand2)
    0.upto(strand1.length).count do |i|
      strand1[i] != strand2[i]
    end
  end
end
