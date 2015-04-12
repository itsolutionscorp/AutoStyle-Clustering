class Hamming
  def compute(strand1, strand2)
    # if strands are different lengths, only compare
    # base pairs up to the length of the smaller strand
    length_to_compare = [strand1.length, strand2.length].min
    bases1 = strand1[0...length_to_compare].chars
    bases2 = strand2[0...length_to_compare].chars

    bases1
      .zip(bases2)
      .select{|base1, base2| base1 != base2}
      .count
  end
end
