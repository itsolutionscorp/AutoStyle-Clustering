class Hamming
  def self.compute(strand_1, strand_2)
    if strand_1.length > strand_2.length
      strand_1, strand_2 = strand_2, strand_1
    end
    difference(strand_1, strand_2)
  end

  def self.difference(strand_1, strand_2)
    strand_1.length.times.count { |i| difference?(strand_1[i], strand_2[i]) }
  end

  def self.difference?(strand_1, strand_2)
    if strand_1.length <= 1
      strand_1 != strand_2[0]
    else
      difference(strand_1, strand_2) > 0
    end
  end
end
