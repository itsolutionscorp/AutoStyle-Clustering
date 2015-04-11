class DNA
  DNA_NUCLEOTIDES = ['G', 'T', 'C', 'A']
  RNA_NUCLEOTIDES = ['G', 'A', 'U', 'C']

  def initialize(nucleotides)
    @nucleotides = nucleotides.chars.each_with_object(Array.new()){
      |nucleotide, nucleotides|
      nucleotides << nucleotide
    }
  end
  
  def count(nucleotide)
    if DNA_NUCLEOTIDES.include?(nucleotide) or RNA_NUCLEOTIDES.include?(nucleotide)
      @nucleotides.count(nucleotide)
    else
      raise ArgumentError
    end
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}){
      |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    }
  end  
end
