class DNA
  # Takes a string like "GATTACA".
  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide)
    ensure_valid_nucleotide(nucleotide)
    @strand.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotide.all_in_dna.each_with_object({}) { |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    }
  end

  private

  def ensure_valid_nucleotide(nucleotide)
    unless Nucleotide.valid?(nucleotide)
      raise ArgumentError, "Not a nucleotide base: #{nucleotide}"
    end
  end
end


module Nucleotide
  # http://en.wikipedia.org/wiki/Nucleic_acid_notation
  BASES = [
    ADENINE  = "A",
    CYTOSINE = "C",
    GUANINE  = "G",
    THYMINE  = "T",
    URIDINE  = "U"
  ]

  def self.all_in_dna
    BASES - [URIDINE]
  end

  def self.valid?(candidate)
    BASES.include?(candidate)
  end
end
