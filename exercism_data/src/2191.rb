class Hamming
  def compute(firstStrand, secondStrand)
    minLength = [firstStrand.length, secondStrand.length].min
    firstStrandAsArray = firstStrand[0..minLength-1].split(//)
    secondStrandAsArray = secondStrand[0..minLength-1].split(//)
    zippedArrayOfStrands = firstStrandAsArray.zip(secondStrandAsArray)
    zippedArrayOfStrands.count{|x| x[0] != x[1]}
  end
end
