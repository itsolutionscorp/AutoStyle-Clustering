def compute sequence1, sequence2
    hamming = 0
    (0..sequence1.length - 1).each do |position|
      if sequence2[position] 
        hamming += 1 unless sequence1[position] == sequence2[position]
      end
    end
    hamming
  end