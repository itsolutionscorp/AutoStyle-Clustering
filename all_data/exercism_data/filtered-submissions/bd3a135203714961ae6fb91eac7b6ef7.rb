def compute(strand_1, strand_2)
    deviation_count = 0
    if strand_1.length <= strand_2.length
      strand_1.length.times do |i|
        if strand_1.split('')[i] != strand_2.split('')[i]
          deviation_count += 1
        end
      end
    else
      strand_2.length.times do |i|
        if strand_1.split('')[i] != strand_2.split('')[i]
          deviation_count += 1
        end
      end
    end
    return deviation_count
  end