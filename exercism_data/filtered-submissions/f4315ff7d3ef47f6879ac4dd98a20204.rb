def compute(strand1, strand2)
    diff = 0
    strand1.chars.each_with_index do |char1, index|
      char2 = strand2[index]
      diff += 1 if char2 && char1 != char2
    end
    diff
  end