def compute(strand, other_strand)



    zipped_strands = strand.codepoints.zip(other_strand.codepoints)
    zipped_strands.take_while { |a, b| b }.count { | a, b | a != b }
  end