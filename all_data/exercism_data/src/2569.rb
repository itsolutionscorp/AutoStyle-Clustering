def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).select do |(c1, c2)|
      c1 != c2
    end.size
  end
end