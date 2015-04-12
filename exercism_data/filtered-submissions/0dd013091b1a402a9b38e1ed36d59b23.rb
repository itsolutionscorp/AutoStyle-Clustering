def compute( dna1, dna2 )
    dna1.chars.zip(dna2.chars).count {|na1, na2| na1 != na2}
  end