def compute(strandA, strandB)
    distance = 0
    (0...strandA.length).each do |i|
      distance += 1 if not strandB[i].nil? and strandA[i] != strandB[i]
    end
    distance
  end