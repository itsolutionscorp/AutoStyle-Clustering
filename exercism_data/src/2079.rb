def compute(strand1, strand2)
    if strand1 == strand2
      return 0
    end

    strand1 = strand1.split("")
    strand2 = strand2.split("")
    comparisonlength = if strand1.length > strand2.length
      strand2.length
    else
      strand1.length
    end
    x = 0
    score = 0
    while x < comparisonlength
      if strand1[x] != strand2[x]
        score+=1
      end

      x+=1
    end
    score
  end