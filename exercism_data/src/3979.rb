def compute strand_a, strand_b

    # prepare input
    strand_a = strand_a.slice(0, strand_b.length).chars
    strand_b = strand_b.chars

    # combine and count differences
    strand_a.zip(strand_b).count do |a, b|
      a != b
    end
  end