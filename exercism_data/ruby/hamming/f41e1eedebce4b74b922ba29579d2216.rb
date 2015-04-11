class Hamming

  def self.compute(base, comparison)
    base_strand = Strand.new(base)
    comparison_strand = Strand.new(comparison)
    HammingCalculator.new(base_strand, comparison_strand).distance
  end

end

class Strand
  def initialize(sequence)
    @sequence = sequence
  end

  def nucleotides
    @sequence.each_char
  end

end

class HammingCalculator

  def initialize(base_strand, other_strand)
    @base_strand = base_strand
    @other_strand = other_strand
  end

  def distance
    base_nucleotides.zip(other_nucleotides).count do |base_nucleotide, other_nucleotide|
      break unless other_nucleotide
      base_nucleotide != other_nucleotide
    end || 0
  end

  private

  def base_nucleotides
    @base_strand.nucleotides
  end

  def other_nucleotides
    @other_strand.nucleotides
  end

end
