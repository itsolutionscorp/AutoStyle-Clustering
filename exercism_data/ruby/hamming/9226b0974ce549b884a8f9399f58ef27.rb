class Hamming
  def self.compute strand_1, strand_2
    (0...[strand_1.length, strand_2.length].min).count do |i|
      strand_1[i] != strand_2[i]
    end
  end
end
