class Hamming
  class  << self
    def  compute(strandOne, strandTwo)
      combined = strandOne.chars.zip(strandTwo.chars)
      combined.count { |a, b| a != b }
    end
  end
end
