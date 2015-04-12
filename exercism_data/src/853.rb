def compute(strandA, strandB)
    strandA = strandA.split('')
    strandB = strandB.split('')
    distance = 0

    strandA.each_with_index do |elementA, index|
      elementB = strandB[index]
      distance += 1 if elementA != elementB
    end

    distance
  end