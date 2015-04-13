def compute(a, b)
    first_strand = a.split('').to_a
    second_strand = b.split('').to_a
    difference = first_strand.zip(second_strand).find_all { |i, j| i != j }.count
  end