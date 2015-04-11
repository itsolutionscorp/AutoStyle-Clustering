class DNATest < MiniTest::Unit::TestCase
  class DNA
    def initialize(string)
      @string = string
      @counts = {"A"=> 0, "T" => 0, "C"=> 0, "G"=> 0}
      dna_validation
    end
    
    
    def count(element)
        @element = element
        element_validation
        @array = @string.chars.sort
        @array.count(element)
    end
    
    def nucleotide_counts
      nucleotides = @string.chars.sort
      nucleotides.each do  |nucleotide| 
        @counts[nucleotide] += 1
      end
      @counts
    end
    
    def dna_validation
      return 0 if @string.empty? 
      raise ArgumentError, "DNA is not RNA" if @string.include?('U')
      raise ArgumentError, "This is not a DNA" unless @string.match(/[ACGT]/)
      
    end
    
    def element_validation
      raise ArgumentError, "There are no such nucleotide" unless @element.match(/[ACGTU]/)
    end
  end
end
