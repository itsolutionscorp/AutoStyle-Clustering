def compute(dna1, dna2)
    diffs = dna1.chars.zip(dna2.chars).partition{ |base1, base2| base1 != base2 }
    diffs.first.length unless diffs.flatten.include? nil
  end