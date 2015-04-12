def compute(strand1, strand2)
    differences = strand1.size < strand2.size ? strand1.size : strand2.size
    differences.times { |i| differences -= 1 if strand1[i] == strand2[i] }
    differences
  end
end

include Hamming