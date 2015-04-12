def compute(strand1, strand2)
    return 0 if strand1 == strand2

    sequence1 = strand1.split //
    sequence2 = strand2.split //

    sequence1, sequence2 = sequence2, sequence1 if sequence2.length < sequence1.length
    pairs = sequence1.zip sequence2
    pairs.count { |s1,s2| s1 != s2 }
  end
end