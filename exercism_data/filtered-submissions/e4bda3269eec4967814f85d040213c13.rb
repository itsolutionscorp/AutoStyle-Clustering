def compute(original_strand, new_strand)
    mutations = 0
    original_strand.split("").each_with_index do |nucleotide, index|
      if new_strand[index]
        mutations += 1 unless new_strand.split("")[index] == nucleotide
      end
    end
    mutations
  end