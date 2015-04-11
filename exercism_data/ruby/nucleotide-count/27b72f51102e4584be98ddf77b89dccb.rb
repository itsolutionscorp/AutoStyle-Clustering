class DNA

  def initialize(string)
    @dna_sequence = string
  end

  def count(nucleotide)  
    raise ArgumentError.new("Not valid nucleotides") unless valid_nucleotides?(nucleotide)
    @dna_sequence.count(nucleotide)
  end

  def nucleotide_counts
    nuc_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

    @dna_sequence.each_char { |nucleotide| nuc_counts[nucleotide] += 1 }

    nuc_counts
  end

  def valid_nucleotides?(nucleotide)
    %w(A T C G U).include?(nucleotide)
  end
end
