def compute(strand1, strand2)
    raise ArgumentError, 'Lengths must be equal' unless strand1.length == strand2.length

    strand1.chars.zip(strand2.chars).count do |nucleotide1, nucleotide2|
      nucleotide1 != nucleotide2
    end
  end