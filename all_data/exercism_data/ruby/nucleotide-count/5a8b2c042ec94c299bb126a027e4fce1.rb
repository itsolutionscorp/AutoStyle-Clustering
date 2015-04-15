class DNA
  
  DNA_NUCLEOTIDES = %W{ A C T G }
  RNA_NUCLEOTIDES = %W{ A C G U }
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES.dup.concat(RNA_NUCLEOTIDES).uniq
    
  def initialize sequence
    raise ArgumentError if (is_rna?(sequence) || !is_dna?(sequence))
    @sequence = sequence
  end
  
  def count nucleotide
    raise ArgumentError unless ALL_NUCLEOTIDES.include? nucleotide
    @sequence.chars.count nucleotide
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, result|
      result[nucleotide] = count nucleotide
    end
  end
  
  def is_rna? sequence
    sequence.chars.to_a == RNA_NUCLEOTIDES
  end
  
  def is_dna? sequence
    sequence.empty? || sequence.chars.all? { |char| DNA_NUCLEOTIDES.include? char }
  end
end
