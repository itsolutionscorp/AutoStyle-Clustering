def compute(sequence1, sequence2)
    # Validating lengths of sequences
    lenvalid = [sequence1.length, sequence2.length].min
    # for each mutation in the sequences, add 1 to compute Hamming Distance
    mutations = 0
    (0..lenvalid-1).each do |i|
     mutations += 1 unless sequence1[i] == sequence2[i]
    end
    mutations
  end