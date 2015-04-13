def compute(strand_a, strand_B)




    differences = 0


    checks_to_do = [strand_a.length, strand_B.length].min

    checks_to_do.times do |i|
      differences += 1 if strand_a[i] != strand_B[i]
    end
    differences
  end