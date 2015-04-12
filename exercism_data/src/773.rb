def compute(s1, s2)
    # distance = 0
    # 0.upto([s1.size,s2.size].min) do |i|
    #   distance = distance + 1 unless s1[i] == s2[i]
    # end
    # distance
    0.upto([s1.size, s2.size].min).count do |i|
      s1[i] != s2[i]
    end
  end