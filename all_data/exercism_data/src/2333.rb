def compute(strand_1, strand_2)
    mutations = 0

    strand_1_array = strand_1.split("")
    strand_2_array = strand_2.split("")

    strand_1_array.each_with_index do |val, index|
      mutations += 1 if strand_2_array[index] != val
    end
    mutations
  end