class DNA

  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  RNA_NUCLEOTIDES = ['A', 'C', 'G', 'U']

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless is_valid_nucleotide(nucleotide)
    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |n, hash|
      hash[n] = self.count(n)
    end
  end

  private

  def is_valid_nucleotide(nucleotide)
    DNA_NUCLEOTIDES.include?(nucleotide) || RNA_NUCLEOTIDES.include?(nucleotide) 
  end

end
