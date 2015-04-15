def compute(strandOne, strandTwo)
    hammingDist = 0
    if(strandOne.eql?(strandTwo)) then return 0 end
    until(strandOne.empty? or strandTwo.empty?) do
      unless (strandOne.slice!(0) == strandTwo.slice!(0)) then hammingDist += 1 end
    end
    hammingDist
  end