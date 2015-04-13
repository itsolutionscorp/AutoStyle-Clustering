def compute(strand1,strand2)


    length=(strand1.length<strand2.length ? strand1.length : strand2.length)-1


    distance=0

    for x in 0..length
      if strand1[x] != strand2[x]
        distance += 1
      end
    end

    return distance
  end