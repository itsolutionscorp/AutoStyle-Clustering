def compute(one,two)
    dist = 0
    0.upto(one.length).each do |i|
      dist += 1 if one[i] != two[i]
    end
    dist
  end