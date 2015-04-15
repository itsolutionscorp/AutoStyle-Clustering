#!/usr/bin/env ruby

class Hamming
  def self.compute(strandA, strandB)

    @A, @B = strandA.split(""), strandB.split("")

    diff = []

    case
    when @A.size == @B.size
       @A.each do |a| 
         diff << compare(a, @B[@A.index(a)])
       end
    when @A.size < @B.size
      newsize = @A.size
      @B = @B[0...newsize]
      @A.each do |a| 
        diff << compare(a, @B[@A.index(a)])
      end
    when @A.size > @B.size
      newsize = @B.size
      @A = @A[0...newsize]
      @B.each do |b| 
        diff << compare(b, @A[@B.index(b)])
      end
    end

    diff.count(false)
    

  end

  # DRY.attempt: This code is wrong, but I haven't yet figured out to use it 
  # correctly!
  #
  # def diff_collect(aryA, aryB)
  #   aryA.each do |a| 
  #     diff << compare(a, aryB[aryA.index(a)])
  #   end
  # end
  #

  def self.compare(a, b)
    a == b
  end

end
