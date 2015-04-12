def compute(s1,s2)
    distance = 0
    for i in 0..s1.length-1
      if i<s2.length
        if s1[i]!=s2[i]
          distance += 1
        end
      end
    end
    return distance
  end