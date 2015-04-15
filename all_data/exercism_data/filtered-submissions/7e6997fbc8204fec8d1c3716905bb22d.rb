def compute(strand1, strand2)
    strand1, strand2 = strand1.to_a, strand2.to_a
    strand1.each_with_index.map { |s1, i| s1 <=> strand2[i] }.compact.map(&:abs).reduce(:+)
  end