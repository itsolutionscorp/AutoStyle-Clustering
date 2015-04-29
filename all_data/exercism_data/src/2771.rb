def compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).inject(0) { |matches, pair| pair[0] != pair[1] ? matches += 1 : matches }