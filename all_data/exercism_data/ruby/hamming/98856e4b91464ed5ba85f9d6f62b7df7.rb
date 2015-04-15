class Hamming


  def self.compute(strand1, strand2)
    DnaDifferenceCalculator.new(strand1, strand2).compare_strands
  end

end

class DnaDifferenceCalculator

  def initialize(strand1, strand2)
    @dna_strand1 = strand1
    @dna_strand2 = strand2
  end    

  def compare_strands
    diff_count = corresponding_nucleotides.count do |pair|
      pair[0] != pair[1] unless pair[1] == nil
    end
    diff_count
  end

  private

  def strand1_nucleotides
    @dna_strand1.chars
  end

  def strand2_nucleotides
    @dna_strand2.chars
  end

  def corresponding_nucleotides
    strand1_nucleotides.zip(strand2_nucleotides)
  end

end
