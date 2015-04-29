class Hamming
  def self.compute(strandOne, strandTwo)
    hammingDist = 0
    if(strandOne.eql?(strandTwo)) then return 0 end # The two strands are equal
    until(strandOne.empty? or strandTwo.empty?) do #Keep slicing nucleotides off until there are no more
      unless (strandOne.slice!(0) == strandTwo.slice!(0)) then hammingDist += 1 end #They aren't equal so we've gotta do some testing.  
    end
    return hammingDist
  end
end
