def compute(strand_one, strand_two)
    strand_one.upcase!
    strand_two.upcase!

    strand_one_length = strand_one.length
    strand_two_length = strand_two.length

    difference   = 0
    count        = 0

    while count <= strand_one_length
      if strand_one[count] != nil && strand_two[count] != nil
        if strand_one[count] != strand_two[count]
          difference += 1
        end
      end
      count += 1
    end

    return difference
  end