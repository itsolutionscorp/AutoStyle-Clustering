class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError, "'#{nucleotide}' is not a nucleotide." unless Nucleotides.nucleotide?(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotides::DNA.reduce(Hash.new) do |data, nucleotide|
      data[nucleotide] = count(nucleotide)
      data
    end
  end
end

class Nucleotides
  DNA = %w(A T C G)
  ALL = DNA + %w(U)

  def self.nucleotide?(token)
    ALL.include?(token)
  end
end
