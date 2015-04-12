def compute(strandOne, strandTwo)
    distance = 0
    nucleotidesOne = strandOne.split('')
    nucleotidesTwo = strandTwo.split('')

    if nucleotidesOne.count == nucleotidesTwo.count
      nucleotidesOne.each_with_index do |nucleotide, indx|
        distance += 1 if nucleotide != nucleotidesTwo[indx]
      end
    end

    distance
  end