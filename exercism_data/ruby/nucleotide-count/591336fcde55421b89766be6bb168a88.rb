class DNA

  def initialize nucleotides 
    @nucleotides = nucleotides
  end

  def count nucleotide
    if check_valid_nucleotide?(nucleotide)
      @nucleotides.count(nucleotide)
    else
      raise ArgumentError, "#{nucleotide} is not a valid nucleotide!"
    end
  end

  def nucleotide_counts
    @nucleotides.split("").each_with_object(default_sequence) { |nucleotide, sequence| sequence[nucleotide]+= 1 }
  end

  private

  def check_valid_nucleotide? nucleotide
    default_sequence.include?(nucleotide) or nucleotide == 'U'
  end

  def default_sequence
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

end
