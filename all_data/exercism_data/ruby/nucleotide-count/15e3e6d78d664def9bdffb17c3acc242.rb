class DNA
  
  DNA_NUCLEOTIDES = %W{ A C T G }
  RNA_NUCLEOTIDES = %W{ A C G U }
    
  def initialize sequence
    @sequence = Sequence.new(sequence)
    raise ArgumentError unless @sequence.valid_dna_sequence?
  end
  
  def count nucleotide
    raise ArgumentError unless (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).include? nucleotide
    @sequence.chars.count nucleotide
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, result|
      result[nucleotide] = count nucleotide
    end
  end
  
  class Sequence
    
    attr_reader :sequence
    
    def initialize sequence
      @sequence = sequence 
    end
    
    def valid_dna_sequence?
      sequence.empty? || sequence.chars.all? { |char| DNA_NUCLEOTIDES.include? char }
    end
    
    def chars
      @sequence.chars
    end
    
  end
      
end
