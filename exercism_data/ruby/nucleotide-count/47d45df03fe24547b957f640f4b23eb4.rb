module Nucleotide
  ADENOSINE = 'A'
  CYTIDINE  = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URACIL    = 'U'

  def nucleotides
    [ADENOSINE, CYTIDINE, GUANOSINE, THYMIDINE, URACIL]
  end

  def nucleotide?(molecule)
    nucleotides.include? molecule
  end

  def count_molecules(sequence, molecules)
    molecules.each_with_object(Hash.new(0)) do |molecule, counts|
      counts[molecule] = sequence.count molecule
    end
  end
end

class DNA
  include Nucleotide

  NUCLEOTIDES = [ADENOSINE, CYTIDINE, GUANOSINE, THYMIDINE]
  attr_reader :nucleotide_counts

  def initialize(sequence)
    @nucleotide_counts = count_molecules(sequence, DNA::NUCLEOTIDES)
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide? nucleotide
    nucleotide_counts[nucleotide]
  end

end
