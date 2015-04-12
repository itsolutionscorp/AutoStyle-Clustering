def compute(dna_seq_1, dna_seq_2)
    diff = 0
    dna_seq_1.each_char.to_a.each_with_index do |e, i|
      diff = diff + 1 if e != dna_seq_2[i]
    end
    diff
  end