def compute(strand_1, strand_2)
    distance = 0
    if strand_1.length != strand_2.length
      return
    else
      strand_1_ntides = strand_1.split(//)
      strand_2_ntides = strand_2.split(//)

      for i in 0..strand_1_ntides.length-1
        if strand_1_ntides[i] != strand_2_ntides[i]
          distance += 1
        end
        i += 1
      end
      return distance
    end
  end