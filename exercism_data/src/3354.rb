def compute(strand1, strand2)
    hamming_distance = 0

    strand1.length.times do |nucleotide|
      hamming_distance += 1 unless strand1[nucleotide] == strand2[nucleotide]
    end

	hamming_distance
  end