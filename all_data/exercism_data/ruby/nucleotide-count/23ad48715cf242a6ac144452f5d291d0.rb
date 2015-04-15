class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + %w(U)

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError, "'#{nucleotide}' is not a nucleotide." unless Nucleotides::ALL.include?(nucleotide)
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
end
