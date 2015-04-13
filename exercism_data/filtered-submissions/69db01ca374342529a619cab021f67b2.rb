def compute(sequence1, sequence2)

    lenvalid = [sequence1.length, sequence2.length].min

    mutations = 0
    (0..lenvalid-1).each do |i|
     mutations += 1 unless sequence1[i] == sequence2[i]
    end
    mutations
  end