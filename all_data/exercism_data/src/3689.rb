def compute(strand1, strand2)
    hamming_distance = 0

    # Turn each strand into an array
    strand1 = strand1.chars
    strand2 = strand2.chars

    # Make both strands the same length
    if strand1.length > strand2.length
      strand1 = strand1.slice(0...strand2.length)
    elsif strand2.length > strand1.length
      strand2 = strand2.slice(0...strand1.length)
    end

    # Iterate through strand1, incrementing
    # the Hamming distance with each inequality
    strand1.each_with_index { |char, idx|
      if char != strand2[idx] then hamming_distance += 1 end
    }

    return hamming_distance
  end