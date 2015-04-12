def compute first_strand, second_strand
    (0..first_strand.length).count { |i| first_strand[i] != second_strand[i] }
  end