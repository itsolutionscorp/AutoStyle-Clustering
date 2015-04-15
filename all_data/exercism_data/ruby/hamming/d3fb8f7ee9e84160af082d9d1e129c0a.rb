class Hamming

  def self.compute(sample_1, sample_2)
    dna_strand_1 = dna_nucleotides(sample_1)
    dna_strand_2 = dna_nucleotides(sample_2)

    compare_strands(dna_strand_1, dna_strand_2)
  end

  def self.dna_nucleotides(strand)
    strand.chars
  end

  def self.compare_strands(dna_1, dna_2)
    difference = 0

    dna_1.each_with_index do |letter, index|
      difference += 1 unless dna_2[index] == nil || letter == dna_2[index]
    end

    difference
  end

end
