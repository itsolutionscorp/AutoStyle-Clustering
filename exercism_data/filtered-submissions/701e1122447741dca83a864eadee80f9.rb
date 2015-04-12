def compute(strand1, strand2)

    if strand1.length == strand2.length
      chars_to_count = strand1.length
    elsif strand1.length < strand2.length
      chars_to_count = strand1.length
    else
      chars_to_count = strand2.length
    end

    hamming = 0

    chars_to_count.times do |n|
      if strand1[n] != strand2[n]
        hamming += 1
      end
    end

    hamming
  end