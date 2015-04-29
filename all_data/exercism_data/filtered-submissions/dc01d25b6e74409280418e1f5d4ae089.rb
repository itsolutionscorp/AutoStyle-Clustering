def compute(strand1, strand2)

    hamming_distance, i = 0, 0


    strand1 = strand1.chars
    strand2 = strand2.chars


    if strand1.length > strand2.length
      strand1 = strand1.slice(0...strand2.length)
    elsif strand2.length > strand1.length
      strand2 = strand2.slice(0...strand1.length)
    end



    strand1.each do |x|
      if x != strand2[i]
        hamming_distance += 1
      end
      i += 1
    end

    return hamming_distance
  end