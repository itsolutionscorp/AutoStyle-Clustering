def compute(strand_a, strand_b)
    distance = 0
    length_a, length_b = strand_a.size, strand_b.size
    nucleotides_to_check = [length_a, length_b].min
    0.upto(nucleotides_to_check - 1) do |i|
      distance += 1 if strand_a[i] != strand_b[i]
    end
    distance
  end