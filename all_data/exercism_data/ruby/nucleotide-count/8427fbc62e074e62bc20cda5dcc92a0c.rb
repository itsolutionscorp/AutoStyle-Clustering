class DNA
  DNA_NUCLEOTIDES = %w[A T C G]
  RNA_NUCLEOTIDES = %w[A C G U]
  NUCLEOTIDES = (DNA_NUCLEOTIDES+RNA_NUCLEOTIDES).uniq

  def initialize string
    @dna = string
  end

  def count nucleotide
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    dna_bits.count nucleotide
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, hash|
      hash[nucleotide] = count nucleotide
    end
  end

  private

  def dna
    @dna
  end

  def dna_bits
    @dna_bits ||= dna.chars
  end
end
