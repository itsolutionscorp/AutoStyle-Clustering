class Hamming

  def Hamming.compute(first_strand, second_strand)

    difference =  0
    length_index = [first_strand.length, second_strand.length].min - 1

    strands = [ first_strand[0..length_index].split(""),
               second_strand[0..length_index].split("") ]

    strands[0].each_with_index { |gene, index| difference += 1 unless strands[1][index].include?(gene) }

    return difference

  end

end
