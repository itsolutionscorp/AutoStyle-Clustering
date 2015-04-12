def compute(strand_1, strand_2)
    length_1 = strand_1.length
    length_2 = strand_2.length

    if length_1 < length_2
      strand_2 = strand_2[0...length_1]
    elsif length_2 < length_1
      strand_1 = strand_1[0...length_2]
    end

    strand_1.each_char.select.with_index { |l, i|
      l != strand_2[i]
    }.length
  end