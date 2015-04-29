def compute(strand1, strand2)
    difference_count = 0
    [strand1.length, strand2.length].min.times do |i|
      difference_count += 1 unless strand1[i] == strand2[i]
    end

    difference_count
  end