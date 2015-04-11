class Hamming
  def self.compute(strand1, strand2)
    last_nucleotide_index = [strand1.length, strand2.length].min - 1

    (0..last_nucleotide_index).count do |i|
      strand1[i] != strand2[i]
    end
  end
end
