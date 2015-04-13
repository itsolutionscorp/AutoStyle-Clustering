def compute strand_a, strand_b


    strand_a = strand_a.slice(0, strand_b.length).chars
    strand_b = strand_b.chars


    strand_a.zip(strand_b).count do |a, b|
      a != b
    end
  end