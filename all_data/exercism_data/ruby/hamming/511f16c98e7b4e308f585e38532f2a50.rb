 class Hamming

  def self.compute(strand1, strand2)
    compare_strands(strand1, strand2)
  end

  def self.compare_strands(strand1, strand2)
    paired_nucleotides = strand1.chars.zip(strand2.chars)
    hamming_count(paired_nucleotides)  
  end

  def self.hamming_count(paired_nucleotides)
    paired_nucleotides.count do |nucleotide_a, nucleotide_b| 
      mutated?(nucleotide_a, nucleotide_b)
    end
  end

  def self.mutated?(nucleotide_a, nucleotide_b)
    nucleotide_a != nucleotide_b unless nucleotide_b == nil
  end
end
