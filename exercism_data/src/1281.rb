def compute(strand1, strand2)

    if strand1.length == strand2.length
      "Wrong length. The strands have to be the same length"
    end

    splitted_strand_1 = strand1.chars
    splitted_strand_2 = strand2.chars

    zipped_strands = splitted_strand_1.zip(splitted_strand_2)

    zipped_strands.count do |s1, s2|
      s1 != s2
    end
  end