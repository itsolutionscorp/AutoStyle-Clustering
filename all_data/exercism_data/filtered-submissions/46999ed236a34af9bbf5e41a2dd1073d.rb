def compute(dna_one, dna_two)
    dna_one.chars.zip(dna_two.chars).count do |chr1, chr2|
      chr1 != chr2
    end
  end