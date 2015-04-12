class Hamming

  def compute(strand1, strand2)

    # length of strand computing
    count = strand1.length

    # puts characters in strand into an array
    strand1 = strand1.chars.to_a
    strand2 = strand2.chars.to_a

    differences = 0
    length = 0

    # loops through strands comparing each character
    until length == count
      if strand1[length] != strand2[length]
       differences += 1
      end
      length += 1
    end

    return differences

  end
end
