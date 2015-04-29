class DNA

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide_letter)
    array_letters = ["A", "G", "C", "T", "U" ]
    if false == array_letters.include?(nucleotide_letter)
        raise ArgumentError
    end
    @strand.count(nucleotide_letter)
  end 

  def nucleotide_counts
    counts_nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    counts_nucleotides['A'] = @strand.count("A")
    counts_nucleotides['T'] = @strand.count("T")
    counts_nucleotides['C'] = @strand.count("C")
    counts_nucleotides['G'] = @strand.count("G")
     counts_nucleotides
  end
end
