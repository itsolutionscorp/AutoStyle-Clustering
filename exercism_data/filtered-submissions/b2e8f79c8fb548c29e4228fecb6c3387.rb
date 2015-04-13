def compute(strandA, strandB)
    distance = 0
    if strandA.size == strandB.size
      strandA.split('').each_with_index do |v,i|
        distance=distance+1 if strandB[i].chr!=v
      end
    end
    distance
  end