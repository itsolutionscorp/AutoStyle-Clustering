def compute(strand_a, strand_b)
      strand_a.chars.each.with_index.reduce(0) do |distance, (nucleotide, index)|
        distance += 1 if nucleotide != strand_b[index]
        distance
      end
    end