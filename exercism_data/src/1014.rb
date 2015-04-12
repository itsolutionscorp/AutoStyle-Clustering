class Hamming
  
  def compute(strand, other_strand)
    # From "ATGC" and "ATC" this
    # creates [ ['A', 'A'], ['T', 'T'], ['G', 'C'], ['C', nil]]
    # and we then count the ones that are different but not nil.
    zipped_strands = strand.codepoints.zip(other_strand.codepoints)
    zipped_strands.take_while { |a, b| b }.count { | a, b | a != b }
  end

end
