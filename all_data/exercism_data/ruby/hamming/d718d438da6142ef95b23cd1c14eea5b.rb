class Hamming

  def self.compute(strand_a, strand_b)
    hamming_distance = 0

    length = Hamming.compare(strand_a, strand_b)

    length.times do |index|
      hamming_distance += 1 if strand_a[index] != strand_b[index]
    end
    hamming_distance
  end

  def self.compare(strand_a, strand_b)
    return  strand_a.length if strand_a.length < strand_b.length 
    strand_b.length
  end

end
