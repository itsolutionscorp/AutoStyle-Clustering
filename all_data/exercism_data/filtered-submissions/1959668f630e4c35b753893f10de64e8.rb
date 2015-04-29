def compute(strand1, strand2)
    comparison_array = strand1.chars.zip(strand2.chars)
    comparison_array.count do |comparison_pair|
      comparison_pair.first != comparison_pair.last
    end
  end