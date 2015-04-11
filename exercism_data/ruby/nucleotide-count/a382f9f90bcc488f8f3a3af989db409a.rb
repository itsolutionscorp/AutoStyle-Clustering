class DNA

  NUCLEOTIDES = ['A', 'T', 'C', 'G', 'U']

  def initialize nucleotides 
    @nucleotides = nucleotides
  end

  def count nucleotide
    if check_valid_nucleotide?(nucleotide)
      @nucleotides.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    default_sequence = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @nucleotides.split("").each_with_object(default_sequence) { |nucleotide, sequence| sequence[nucleotide]+= 1 }
  end

  private

  def check_valid_nucleotide? nucleotide
    NUCLEOTIDES.include?(nucleotide)
  end

end
