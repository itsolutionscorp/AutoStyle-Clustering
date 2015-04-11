class Hamming

  def initialize
    @i= 0
    @distance = 0
  end

  def findSmallerNumber(s1,s2)
   s1.length > s2.length ? s2.length : s1.length
  end

  def hammmingCompare (s1,s2)

    lengthOfSmallerString = findSmallerNumber(s1,s2)

    #This was just used to track my code for debug..
    puts " Length of  smaller string #{lengthOfSmallerString}"
    puts "input s1 #{s1} s2 #{s2} i = #{@i} distance = #{@distance} s1,s2.length  = #{s1.length},#{s2.length}"

    while @i<lengthOfSmallerString
      if s1[@i] == s2[@i]
        @i=@i+1
      else
        @distance = @distance+1
        @i=@i+1
      end

    end

    return @distance
  end

end
