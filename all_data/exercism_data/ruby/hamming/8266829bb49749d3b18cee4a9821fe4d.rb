class Hamming
  def self.compute(strand1, strand2)
    strand1 = strand1.split("")
    strand2 = strand2.split("")
    diff = 0

    (0...strand_length(strand1, strand2)).each do |i|
      if strand1[i] != strand2[i]
        diff += 1
      end
    end

    diff
  end

  def self.strand_length(strand1, strand2)
    if strand1.length < strand2.length
      return strand1.length
    else
      return strand2.length
    end
  end

end
