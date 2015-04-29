class Hamming
  def self.compute(strand1, strand2)
    strand1.length.times.count do |i|
      strand1[i] != strand2[i]
    end
  end
end
