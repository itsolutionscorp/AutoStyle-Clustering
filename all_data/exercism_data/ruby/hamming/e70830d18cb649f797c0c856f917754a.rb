class Hamming
  def self.compute(strand1, strand2)
    distance = 0

    unless dna_length_equal?(strand1, strand2)
      strand1 = trim_strand(strand1, strand2.length)
    end

    if strand1 != strand2
      strand1.each_char.with_index do |na, i|
        distance += 1 unless na_equal?(na, strand2[i])
      end
    end
    
    distance
  end

  def self.na_equal?(na1, na2)
    na1 == na2
  end

  def self.dna_length_equal?(strand1, strand2)
    strand1.length == strand2.length
  end

  def self.trim_strand(strand, max_length)
    strand[0...max_length]
  end
end
