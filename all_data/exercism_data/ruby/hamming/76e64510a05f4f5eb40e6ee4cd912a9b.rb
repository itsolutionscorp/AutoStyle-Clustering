class Hamming
  def self.compute(strandA, strandB)
    if strandA.length <= strandB.length
      ret = computeHamming(strandA,strandB)
    else
      ret = computeHamming(strandB,strandA)
    end
    return ret
  end

  private

    def self.computeHamming(a,b)
      sum=0
      a.each_char.with_index do |c,i|
        if b[i] != c 
          sum += 1 
        end
      end
      return sum
    end
end
