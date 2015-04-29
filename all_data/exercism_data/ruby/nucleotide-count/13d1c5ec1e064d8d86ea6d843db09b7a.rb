class DNA
  RNA_NUCLEOTIDES = %w[U]
  DNA_NUCLEOTIDES = %w[A C G T]
  ALL_NUCLEOTIDES = RNA_NUCLEOTIDES + DNA_NUCLEOTIDES
  DNA_VALID = /^[#{DNA_NUCLEOTIDES.join('')}]*$/

  def initialize(string)
    raise ArgumentError.new unless string.match(DNA_VALID)
    @string = string
  end

  def count(nucleotide)
    raise ArgumentError.new unless ALL_NUCLEOTIDES.include? nucleotide
    @string.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nuc, counts|
      counts[nuc] = count(nuc)
    end
  end
end
