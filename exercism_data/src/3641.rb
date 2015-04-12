class Hamming
  
  def compute(strand, other_strand)
    # From "ATGC" and "ATC" this
    # creates [ ['A', 'A'], ['T', 'T'], ['G', 'C'], ['C', nil]]
    # and we then count the ones that are different but not nil.
    codepoints = strand.codepoints.zip(other_strand.codepoints)
    codepoints.count { | a, b | a != b unless b.nil? }
  end

end
