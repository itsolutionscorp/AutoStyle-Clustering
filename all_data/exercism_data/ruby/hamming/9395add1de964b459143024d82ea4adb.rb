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
      #  @A = 'AGAGACTTA'.split("")
      #  @B = 'AAA'.split("")
      newsize = @B.size
      # @B.size=3
      @A = @A[0...newsize]
      # @A = @A
      # 'AAA'.split("") = ["A","A","A"]
      @B.each do |b| 
        # ["A",
        #  "A",
        #  "A"]
        diff << compare(b, @A[@B.index(b)])
        # diff <<
        #   compare("A", @A[@B.index("A")])
        #   compare("A", @A[@B.index("A")])
        #                ["A","G","A","G","A","C","T","T","A"][@B.index("A")]
        #                                                     ^^^^^^^^^^^^^^^
        #
        #                  [@B.index("A")]
        #                  ["A","A","A"].index("A") will always return 0 in this case
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
