def compute(strand_1, strand_2)
    count = 0
    strand_1.split(//).each_index do |i|
      break if i == strand_2.length
      count += 1 unless strand_1[i] == strand_2[i]
    end
    count
  end