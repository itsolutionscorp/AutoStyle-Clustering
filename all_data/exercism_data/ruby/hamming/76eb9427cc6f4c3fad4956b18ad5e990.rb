class Hamming


  def self.compute(strand1, strand2)
    @dna_strand1 = strand1
    @dna_strand2 = strand2
    compare_strands
  end

  private

  def self.compare_strands
    diff_count = corresponding_nucleotides.count do |pair|
      pair[0] != pair[1] unless pair[1] == nil
    end
    diff_count
  end

  def self.strand1_nucleotides
    @dna_strand1.split("")
  end

  def self.strand2_nucleotides
    @dna_strand2.split("")
  end

  def self.corresponding_nucleotides
    strand1_nucleotides.zip(strand2_nucleotides)
  end

end
