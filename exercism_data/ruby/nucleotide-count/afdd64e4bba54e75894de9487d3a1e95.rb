module Nucleotide
  ADENOSINE = 'A'
  CYTIDINE  = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URACIL    = 'U'

  def nucleotide?(molecule)
    [ADENOSINE, CYTIDINE, GUANOSINE, THYMIDINE, URACIL].include? molecule
  end
end

class DNA
  include Nucleotide

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide? nucleotide
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    @nucleotide_counts ||= {
      ADENOSINE => count(ADENOSINE),
      THYMIDINE => count(THYMIDINE),
      CYTIDINE => count(CYTIDINE),
      GUANOSINE => count(GUANOSINE)
    }
  end

end
