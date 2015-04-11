class DNA
  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(other_strand)
    normalised_strands = DNAStrandCutter.snip_to_equal_length(@strand, other_strand.chars)
    normalised_strands.count do |strand, other|
      strand != other
    end
  end

end

class DNAStrandCutter
  def self.snip_to_equal_length(*strands)
    strand, other = strands
    strand.zip(other).reject do |nucleotides|
      Nucleotide.invalid?(nucleotides)
    end
  end
end

class Nucleotide
  BASES = [
    ADENINE  = "A",
    CYTOSINE = "C",
    GUANINE  = "G",
    THYMINE  = "T"
  ]

  def self.invalid?(members)
    members.any?{ |member| !BASES.include? member}
  end

end
