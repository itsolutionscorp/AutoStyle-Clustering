def compute(s1, s2)

    distance = 0

    s1.split("").each_with_index do |nucleotide,index|
      if nucleotide != s2[index]
        distance += 1
      end
    end

    return distance
  end