def compute(strand1, strand2)
    nucleotide_to_compare = if strand1.length < strand2.length
      strand1.chars.zip(strand2.chars)
    else
      strand2.chars.zip(strand1.chars)
    end
    distance = 0
    nucleotide_to_compare.each do |n|
      distance += 1 if n[0] != n[1]
    end
    distance
  end