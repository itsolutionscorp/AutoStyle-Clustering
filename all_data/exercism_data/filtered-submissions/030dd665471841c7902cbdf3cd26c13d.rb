def compute(sequence1, sequence2)
    sequence1.chars.zip(sequence2.chars).count do |symbol1, symbol2|
      symbol2 && symbol2 != symbol2
    end
  end