def compute(strand1, strand2)
    dna_strands = strand1.chars.zip(strand2.chars)

    dna_strands.count do |element|
      !(element[0]==element[1])
    end
  end