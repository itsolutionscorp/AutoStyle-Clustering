def compute(strand_one, strand_two)
    short_strand = [strand_one, strand_two].min{|a,b| a.size <=> b.size }
    hamming_dist = 0

    short_strand.length.times do |i|
      hamming_dist += 1 if strand_one[i] != strand_two[i]
    end

    hamming_dist
  end