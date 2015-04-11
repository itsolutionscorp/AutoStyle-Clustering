class DNA

  def initialize(dna_string)
    raise ArgumentError unless valid_dna_string?(dna_string)
    @dna_string = dna_string
  end  

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    dna_string.count(nucleotide)
  end

  def nucleotide_counts
    { 'A' => count('A'), 
      'T' => count('T'), 
      'C' => count('C'), 
      'G' => count('G')
    }
  end

  private

  attr_reader :dna_string

  def valid_nucleotide?(nucleotide)
    !!/[atcgu]/i.match(nucleotide)
  end

  def valid_dna_string?(dna_string)
    !/[^gtac]/i.match(dna_string)
  end
  
end
