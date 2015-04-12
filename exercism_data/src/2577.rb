def compute(strand_a, strand_b)
    strand_a.split("").zip(strand_b.split("")).select do |a, b|
      !b.nil? && a != b
    end.length
  end