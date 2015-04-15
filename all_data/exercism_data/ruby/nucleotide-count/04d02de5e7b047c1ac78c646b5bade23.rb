class DNA
  def initialize(dna_sequence)
    @dna_sequence = dna_sequence
  end
    
  def count(nucleotide)
    valid_nucleotides = ["A", "C", "G", "T", "U"]
    if valid_nucleotides.include?(nucleotide.upcase)
      nucleotide_counts[nucleotide] || 0  
    else
      raise ArgumentError,
        "#{nucleotide} is not a valid nucleotide"
    end
  end

  def nucleotide_counts
    dna_nucleotide_count = Hash['A' => 0, 'C' => 0, 'G' => 0, 'T' => 0]
    @dna_sequence.upcase.each_char do |char|
      dna_nucleotide_count[char] += 1 if dna_nucleotide_count.has_key?(char) 
    end
    dna_nucleotide_count
  end

end
