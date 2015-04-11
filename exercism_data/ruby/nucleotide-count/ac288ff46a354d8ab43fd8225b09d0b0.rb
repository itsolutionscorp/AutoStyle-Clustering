class DNA
  
  DNA_NUCLEOTIDES = %W{ A C T G }
  RNA_NUCLEOTIDES = %W{ A C G U }
    
  def initialize sequence
    @sequence = sequence.chars.map { |letter| DNA_Nucleotide.new(letter)}
  end
  
  def count char
    @sequence.count Nucleotide.new(char)
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, result|
      result[nucleotide] = count nucleotide
    end
  end
  
  class Nucleotide
    
    def initialize char
      raise ArgumentError unless (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).include? char
      @char = char
    end
    
    def == other
      self.to_s == other.to_s
    end
    
    def to_s
      @char
    end
    
  end
  
  class DNA_Nucleotide < Nucleotide
    
    def initialize char
      raise ArgumentError unless (DNA_NUCLEOTIDES).include? char
      @char = char
    end
    
  end
  
end
