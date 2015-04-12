def compute(strand_one, strand_two)
    first = strand_one.split(//)
    second = strand_two.split(//)
    distance = 0
    first.map.with_index do |x, y|
      if x != second[y] 
        distance = distance + 1
      end
    end
    return distance
  end