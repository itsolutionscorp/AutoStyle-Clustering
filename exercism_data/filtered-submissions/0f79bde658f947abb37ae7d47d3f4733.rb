def compute(strand1, strand2)

    hamming_distance = 0
    longer_strand = strand1
    shorter_strand = strand2
    
    if strand2.length > strand1.length
      longer_strand = strand2
      shorter_strand = strand1
    end
    
    for pos in 0..shorter_strand.length - 1
        if shorter_strand[pos] != longer_strand[pos] 
          hamming_distance += 1
        end
    end

    return hamming_distance
  end