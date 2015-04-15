module Nucleotides
  ADENOSINE = 'A'
  CYTIDINE = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URIDINE = 'U'

  class << self
    def valid_nucleotide? nucleotide
      nucleotides.include? nucleotide
    end

    def nucleotides
      dna_nucleotides + other_nucleotides
    end

    def dna_nucleotides
      [ADENOSINE, CYTIDINE, GUANOSINE, THYMIDINE]
    end

    def other_nucleotides
      [URIDINE]
    end
  end
end

class DNA
  def initialize sequence
    @sequence = sequence
  end

  def count nucleotide
    raise ArgumentError unless Nucleotides.valid_nucleotide? nucleotide
    sequence.count nucleotide
  end

  def nucleotide_counts
    Nucleotides.dna_nucleotides.each_with_object({}) { |nucleotide, hash| hash[nucleotide] = count nucleotide }
  end

  private
  attr_reader :sequence
end
