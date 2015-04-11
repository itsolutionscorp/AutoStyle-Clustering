class Hamming
  def self.compute(strand_a, strand_b)
    strand_a = Strand.new(strand_a)
    strand_b = Strand.new(strand_b)
    strand_a.number_of_nucleotide_differences_with(strand_b)
  end
end

class Strand
  def initialize(strand)
    @strand = strand
  end

  def number_of_nucleotide_differences_with(other)
    nucleotide_pairs_with(other).count do |nuc_a, nuc_b|
      nuc_a != nuc_b
    end
  end

  def nucleotide_pairs_with(other)
    len        = [length, other.length].min
    nucs       = nucleotides.take(len)
    other_nucs = other.nucleotides.take(len)
    nucs.zip(other_nucs)
  end
  private :nucleotide_pairs_with

  def length
    @strand.size
  end

  def nucleotides
    @strand.chars
  end
end
