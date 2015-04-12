class Hamming
  def compute(dna_string_1, dna_string_2)
    dna_sequence_1 = dna_string_1.chars
    dna_sequence_2 = dna_string_2.chars

    paired_sequence = dna_sequence_1.zip(dna_sequence_2)

    paired_sequence.count do |symbol_1, symbol_2|
      symbol_1 != symbol_2
    end
  end
end
