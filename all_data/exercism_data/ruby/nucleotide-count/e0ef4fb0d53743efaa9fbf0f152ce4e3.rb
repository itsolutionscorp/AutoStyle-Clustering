class DNA
  attr_reader :sequence
  
  VALID_NUCLEOTIDES = ["A", "C", "G", "T", "U"]

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError.new("#{nucleotide} is not a valid nucleotide.") unless valid_nucleotide?(nucleotide)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    { "A" => count("A"), "C" => count("C"), "G" => count("G"), "T" => count("T") }   
  end

  protected
    
    def valid_nucleotide?(nucleotide)
      VALID_NUCLEOTIDES.include?(nucleotide)
    end 
end
