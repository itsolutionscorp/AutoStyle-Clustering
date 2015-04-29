class DNA
  
  NUCLEOTIDES = "ATCG".chars
  
  def initialize(dna_string)
    unless (dna_string.chars - NUCLEOTIDES).empty?
      raise ArgumentError, "Invalid nucleotide in #{dna_string}"
    end
    @dna_string = dna_string
  end
  
  def nucleotide_counts
    NUCLEOTIDES.each_with_object({}) do |nucleotide,counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
  
  def count(nucleotide)
    unless (NUCLEOTIDES + ["U"]).include? nucleotide
      raise ArgumentError, "Invalid nucleotide #{nucleotide}"
    end
    @dna_string.count(nucleotide)
  end
end
