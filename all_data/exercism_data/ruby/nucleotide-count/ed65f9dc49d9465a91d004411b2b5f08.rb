class DNA

  DNA_NUCLEOTIDES = ['A', 'C', 'T', 'G']
  RNA_NUCLEOTIDES = ['A', 'C', 'G', 'U']

  def initialize(dna_string)
    dna_string.each_char do | char |
      throw ArgumentError unless DNA_NUCLEOTIDES.include?(char)
    end
    @dna_string = dna_string
  end

  def count(nucleotide)
    throw ArgumentError unless DNA_NUCLEOTIDES.include?(nucleotide) || RNA_NUCLEOTIDES.include?(nucleotide)
    return @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides = Hash.new(0)
    DNA_NUCLEOTIDES.each do | nucleotide |
      nucleotides[nucleotide] = @dna_string.count(nucleotide)
    end

    return nucleotides 
  end
end
