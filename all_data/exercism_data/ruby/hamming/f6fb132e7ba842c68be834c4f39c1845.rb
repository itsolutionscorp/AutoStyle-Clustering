class Hamming
  def self.compute(firstStrand, secondStrand)
    minLength = [firstStrand.length, secondStrand.length].min
    firstStrandAsArray = firstStrand[0..minLength-1].chars
    secondStrandAsArray = secondStrand[0..minLength-1].chars
    zippedArrayOfStrands = firstStrandAsArray.zip(secondStrandAsArray)
    zippedArrayOfStrands.count{|x| x[0] != x[1]}
  end
end
