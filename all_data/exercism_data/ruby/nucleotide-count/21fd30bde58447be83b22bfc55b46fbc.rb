class DNA
  def initialize(sequence)
    valid?(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    valid_nucleotides?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    counter = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    counter.default = 0
    @sequence.each_char { |key| counter[key] += 1 }
    return counter
  end

  def valid?(nucleotide)
    valid_nucleotides?(nucleotide)
    valid_dna?(nucleotide)
  end

  def valid_dna?(nucleotide)
    if nucleotide =~ /[^GATC]/
      raise ArgumentError, "This counts nucleotides in DNA, not RNA"
    end
  end

  def valid_nucleotides?(nucleotide)
    if nucleotide =~ /[^GATCU]/
      raise ArgumentError, "This only counts nucleotides"
    end
  end
end
