class Hamming

  def self.compute(inputA, inputB)
    differenceCount = 0
    inputAArray = inputA.split('')
    inputBArray = inputB.split('')
    smallerArraySize = inputAArray.size - 1
    if inputBArray.size < inputAArray.size
      smallerArraySize = inputBArray.size - 1
    end
    for e in 0..smallerArraySize
      if inputAArray[e] != inputBArray[e]
        differenceCount += 1
      end
    end
    differenceCount
  end

end
