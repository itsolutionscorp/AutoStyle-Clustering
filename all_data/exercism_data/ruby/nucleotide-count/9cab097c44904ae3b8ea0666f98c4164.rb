class DNA
  DNA_NUCLEOTIDES = ['G', 'T', 'C', 'A']
  RNA_NUCLEOTIDES = ['G', 'A', 'U', 'C']

  def initialize(nucleotides)
    @nucleotides = nucleotides.chars
  end
  
  def count(nucleotide)
    if DNA_NUCLEOTIDES.include?(nucleotide) || RNA_NUCLEOTIDES.include?(nucleotide)
      @nucleotides.count(nucleotide)
    else
      raise(ArgumentError, ":nucleotide must be a DNA or RNA nucleotide") 
    end
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}){
      |nucleotide, counts|
      counts[nucleotide] = @nucleotides.count(nucleotide)
    }
  end  
end
