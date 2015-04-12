def compute(dna1, dna2)
    if dna1.length <= dna2.length
      short = dna1
      long = dna2
    else
      short = dna2
      long = dna1
    end

    short.chars
      .zip(long.chars)
      .count { |c1, c2| c1 != c2 }
  end
end