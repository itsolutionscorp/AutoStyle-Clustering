def compute(strand_a, strand_B)
    return 0 if strand_a.eql? strand_B

    count = 0
    checks_to_do = (strand_a.length < strand_B.length) ? strand_a.length : strand_B.length

    checks_to_do.times do |i|
      count += 1 if strand_a[i] != strand_B[i]
    end
    count
  end