class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless Nucleotides.include?(nucleotide)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    {
      Nucleotides::ADENOSINE => count(Nucleotides::ADENOSINE),
      Nucleotides::CYTIDINE => count(Nucleotides::CYTIDINE),
      Nucleotides::GUANOSINE => count(Nucleotides::GUANOSINE),
      Nucleotides::THYMIDINE => count(Nucleotides::THYMIDINE)
    }
  end

  private

  attr_reader :sequence

end

module Nucleotides

  ADENOSINE = "A"
  CYTIDINE = "C"
  GUANOSINE = "G"
  THYMIDINE = "T"
  URACIL = "U"

  def self.include?(nucleotide)
    all.include?(nucleotide)
  end

  def self.all
    [ ADENOSINE, CYTIDINE, GUANOSINE, THYMIDINE, URACIL ]
  end

end
