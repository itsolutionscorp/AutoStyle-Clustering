def compute(dna_string_1, dna_string_2)
    dna_sequence_1 = dna_string_1.split('')
    dna_sequence_2 = dna_string_2.split('')

    paired_sequence = dna_sequence_1.zip(dna_sequence_2)

    paired_sequence.reduce(0) do |hamming_distance, symbol_pair|
      hamming_delta = symbol_pair.first == symbol_pair.last ? 0 : 1
      hamming_distance + hamming_delta
    end
  end