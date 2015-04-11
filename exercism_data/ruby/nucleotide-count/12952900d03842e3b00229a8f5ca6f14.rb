class DNA
  
  DNA_NUCLEOTIDES = %W{ A C G T }
  RNA_NUCLEOTIDES = %W{ A C G U }
    
  def initialize sequence
    @sequence = sequence.chars.map { |letter| Nucleotide.new(letter, DNA_NUCLEOTIDES)}
  end
  
  def count char
    @sequence.count Nucleotide.new(char, (DNA_NUCLEOTIDES+RNA_NUCLEOTIDES))
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, result|
      result[nucleotide] = count nucleotide
    end
  end
  
  class Nucleotide
    
    def initialize char, valid_set
      raise ArgumentError unless valid_set.include? char
      @char = char
    end
    
    def == other
      self.to_s == other.to_s
    end
    
    def to_s
      @char
    end
    
  end
  
end
