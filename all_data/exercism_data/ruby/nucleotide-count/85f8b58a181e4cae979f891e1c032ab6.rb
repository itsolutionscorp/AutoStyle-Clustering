class DNATest < MiniTest::Unit::TestCase
  class DNA
    def initialize(string)
      @string = string #nucleotides
      validate_dna #validates nucleotides
      @counts = {"A"=> 0, "T" => 0, "C"=> 0, "G"=> 0} #default status for @counts
    end


    def count(element)
        @element = element #what elements are we counting
        validate_element #is this element valid
        @array = convert_array(@string) #converts into a sorted array
        @array.count(element) #counts elements in an array
    end


    def nucleotide_counts
      nucleotides = convert_array(@string) #split submitted nucleotides into characters and sort them alphabetically
      nucleotides.each do  |nucleotide|
        @counts[nucleotide] += 1 #goes and counts nucleotides, adding them to a hash named @counts
      end
      @counts #returns counts of nucleotides
    end

    private

    def validate_dna
      return if @string.empty? #if no nucleotides were submitted, returns zero
      raise ArgumentError, "DNA is not RNA" if @string.include?('U') #error if @string includes U
      raise ArgumentError, "This is not DNA" unless @string.match(/[ACGT]/) # unless @string contains A, C, G, or T,
                                                                            #it's not a valid @string.
      end

    def validate_element
      raise ArgumentError, "There are no such nucleotide" unless @element.match(/[ACGTU]/) #
    end

    def convert_array(string)
        string.chars.sort
    end
  end
end
