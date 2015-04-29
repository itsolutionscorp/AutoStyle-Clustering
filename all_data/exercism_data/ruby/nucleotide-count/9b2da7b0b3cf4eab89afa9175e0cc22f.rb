module Nucleotides
  GUANINE   = 'G'
  ADENINE   = 'A'
  THYMINE   = 'T'
  CYTOSINE  = 'C'
  URACIL    = 'U'

  def self.dna
    [ADENINE, THYMINE, CYTOSINE, GUANINE]
  end

  def self.rna
    [ADENINE, CYTOSINE, GUANINE, URACIL]
  end

  def self.all
    dna | rna
  end

  def self.valid? nucleotide
    all.include? nucleotide
  end
end

class DNA
  attr_reader :sequence; private :sequence

  def initialize sequence
    @sequence = sequence
  end

  def nucleotide_counts
    Nucleotides.dna.each_with_object({}) do |nucleotide, result|
      result[nucleotide] = count nucleotide
    end
  end

  def count nucleotide
    ensure_valid_nucleotide nucleotide
    sequence.count nucleotide
  end

  private

  def ensure_valid_nucleotide nucleotide
    unless Nucleotides.valid? nucleotide
      raise ArgumentError, "#{nucleotide} is not a valid nucleotide."
    end
  end
end
