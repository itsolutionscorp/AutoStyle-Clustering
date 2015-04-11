class DNA
  DNA_NUCLEOTIDES = ['A','C','G','T']
  RNA_NUCLEOTIDES = ['A','C','G','U']
  VALID_NUCLEOTIDES = DNA_NUCLEOTIDES | RNA_NUCLEOTIDES
  
  def initialize(string)
    @string = string
  end
  
  def count(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include?(nucleotide)
    @string.count(nucleotide)
  end
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |n, hash|
      hash[n] = count(n)
    end
  end
end
