def compute(strand1, strand2)
    strand1.each_char.with_index.map do |char, i|
      char == strand2[i] ? 0 : 1
    end.reduce(:+)
  end