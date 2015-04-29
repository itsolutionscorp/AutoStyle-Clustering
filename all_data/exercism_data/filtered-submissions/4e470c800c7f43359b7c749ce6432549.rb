def compute(strand1, strand2)


    length_to_compare = [strand1.length, strand2.length].min
    bases1 = strand1[0...length_to_compare].chars
    bases2 = strand2[0...length_to_compare].chars

    bases1
      .zip(bases2)
      .count{|base1, base2| base1 != base2}
  end