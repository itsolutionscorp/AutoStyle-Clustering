def compute(strand1, strand2)
    diff = 0
    strand1.chars.each_with_index do |char, i|
      diff += 1 if strand1[i] != strand2[i]
    end
    diff
  end