def compute(strandOne, strandTwo)
    count = 0
    strandTwoIndex = 0

    strandOne.each_char do |c|
      if !strandTwo[strandTwoIndex].eql? c
        count = count + 1
      end
      strandTwoIndex = strandTwoIndex + 1
    end

    return count
  end