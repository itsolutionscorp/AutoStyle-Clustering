def compute strand1, strand2
    shorter_length = (strand1.length < strand2.length ? strand1.length : strand2.length) - 1
    difference = 0
    0.upto(shorter_length) do |i|
      difference += 1 unless strand1[i] == strand2[i]
    end
    return difference
  end