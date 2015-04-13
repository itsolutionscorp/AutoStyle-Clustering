def compute(dna_seq_1, dna_seq_2)
    hamming_distance = 0

    min_length = dna_seq_1.length <= dna_seq_2.length ? dna_seq_1.length : dna_seq_2.length
    for pos in 0..min_length - 1
      if not dna_seq_1[pos].chr == dna_seq_2[pos].chr
        hamming_distance += 1
      end
    end
      return hamming_distance
  end

  if __FILE__ == $0
    puts Hamming.compute('AG', 'A')
  end