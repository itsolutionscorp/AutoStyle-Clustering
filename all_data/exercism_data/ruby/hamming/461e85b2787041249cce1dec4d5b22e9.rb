class Hamming

  def self.compute(strand1, strand2)
    shared_strand_length(strand1, strand2).times.count do |index|
      strand1[index] != strand2[index]
    end
  end

  def self.shared_strand_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end

end
