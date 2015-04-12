def compute(strand_a, strand_B)
    # not necessary, since comparison does return zero, too
    # return 0 if strand_a.eql? strand_B

    # renaming count to differences to gain better meaning
    differences = 0

    # making use of Array.min more readable than ternary operator
    checks_to_do = [strand_a.length, strand_B.length].min

    checks_to_do.times do |i|
      differences += 1 if strand_a[i] != strand_B[i]
    end
    differences
  end