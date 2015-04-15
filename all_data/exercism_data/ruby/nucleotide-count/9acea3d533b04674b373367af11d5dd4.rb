class DNA
  def initialize(nucleic_acid)
    @nucleic_acid = nucleic_acid
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    @nucleic_acid.scan(/#{nucleotide}/).length
  end

  def nucleotide_counts
    dna_nucleotides.each_with_object({}) {|n, h| h[n] = count(n) || 0 }
  end

  private
  
    def validate_nucleotide(nucleotide)
      raise ArgumentError, "Not a valid nucleotide" unless all_nucleotides.include?(nucleotide)
    end

    def dna_nucleotides
      @dna_nucleotides ||= Nucleotides.dna
    end

    def all_nucleotides
      @all_nucleotides ||= Nucleotides.all
    end
end

module Nucleotides
  ADENINE = "A"
  THYMINE = "T"
  GUANINE = "G"
  CYTOCINE = "C"
  URACIL = "U"

  def self.dna
    [ADENINE, THYMINE, CYTOCINE, GUANINE]
  end

  def self.all
    [ADENINE, THYMINE, CYTOCINE, GUANINE, URACIL]
  end
end
