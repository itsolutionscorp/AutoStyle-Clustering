def compute(strand_a, strand_b)


    diff = 0
    strand_a.chars.each_with_index do |c, index|

      break if strand_b[index].nil?
      diff += 1 unless c == strand_b[index]
    end
    diff
  end