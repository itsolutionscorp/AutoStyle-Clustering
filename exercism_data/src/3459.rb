def compute(dna_seq_1, dna_seq_2)
    dna_seq_1.each_char.with_index.reject { |e, i| e == dna_seq_2[i] }.size
  end