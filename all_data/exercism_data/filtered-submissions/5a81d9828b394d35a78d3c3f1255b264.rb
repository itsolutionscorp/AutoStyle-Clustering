def compute(strand1,strand2)
    distance = 0


    return distance if (strand1 == strand2)


    length_to_slice = (strand1.length > strand2.length ? strand2.length : strand1.length)

    strand1 = strand1[0,length_to_slice]
    strand2 = strand2[0,length_to_slice]


    strand1.chars.each_with_index {|base, i|
      if (base != strand2[i]) then distance = distance+1 end
    }

    return distance
  end