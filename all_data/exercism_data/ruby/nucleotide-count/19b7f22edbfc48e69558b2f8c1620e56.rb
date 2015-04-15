class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + %w(U)

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless ALL_NUCLEOTIDES.include?(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.reduce(Hash.new(0)) do |data, key|
      data[key] = count(key)
      data
    end
  end
end
