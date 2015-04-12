class Hamming
  class << self
    def compute(strA, strB)
      if strA.length > strB.length
        strA,strB = strB, strA
      end
      dist = 0
      strA.length.times do |i|
        dist += 1 if strA[i] != strB[i]
      end
      dist
    end
  end
end
