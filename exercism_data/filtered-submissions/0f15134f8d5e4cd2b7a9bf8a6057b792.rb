def compute dna_sequence, second_dna_sequence
    dna_sequence.chars.zip(second_dna_sequence.chars).delete_if { |pair| pair.any?(&:nil?)}.inject(0) { |result, (a,b)| result += (a <=> b).abs }
  end