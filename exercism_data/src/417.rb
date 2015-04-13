def compute(strand1, strand2)
    strand1.each_char.with_index.inject(0) do |distance, (nucleotide, i)|
      nucleotide == strand2[i] ? distance : distance + 1
    end
  end