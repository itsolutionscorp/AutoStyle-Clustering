def compute(strand, other_strand)



    codepoints = strand.codepoints.zip(other_strand.codepoints)
    codepoints.count { | a, b | a != b unless b.nil? }
  end