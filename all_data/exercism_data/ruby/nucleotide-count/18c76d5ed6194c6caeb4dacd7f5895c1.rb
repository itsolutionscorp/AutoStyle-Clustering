class DNA
  def initialize dna_string
    raise ArgumentError unless valid_dna?(dna_string)
    @dna_string = dna_string
  end

  def count nucleotide
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    { "A" => count('A'), "T" => count('T'), "C" => count('C'), "G" => count('G') }
  end

  private

  def valid_nucleotide?(nucleotide)
    %w(A C G T U).include? nucleotide
  end

  def valid_dna?(dna_string)
    !(dna_string =~ /[^ACGT]/)
  end
end
