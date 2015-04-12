#!/usr/bin/env ruby

class Hamming

  attr_reader :strandA, :strandB
  
  def initialize(strandA, strandB)
    @a = strandA
    @b = strandB
  end

  def self.compute(a, b)

    @A, @B = a.split(""), b.split("")

    diff = []

    
    case
      # assert_equal 1, Hamming.compute(
      # ['AGAGACTTA', 'AAA']
      # ['
      #  AGAGACTTA', 
      # 'AAA
      #   ^ ------
      # ']
    when @A.size == @B.size

      ind = 0
       @A.each do |a| 
        diff << compare(a, @B[ind])
        ind+=1
       end

    when @A.size < @B.size

      # I'm repeating myself
      newsize = @A.size
      @B = @B[0...newsize]
      ind = 0

      @A.each do |a| 
        diff << compare(a, @B[ind])
        ind+=1
      end

    when @A.size > @B.size

      # I'm repeating myself
      newsize = @B.size
      @A = @A[0...newsize]
      ind = 0

      @B.each do |b| 
        diff << compare(b, @A[ind])
        ind+=1
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