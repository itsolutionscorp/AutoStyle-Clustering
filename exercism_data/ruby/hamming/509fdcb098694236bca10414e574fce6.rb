class Hamming
  def self.compute(strandOne, strandTwo)
    count = 0 # stores count of errors
    strandTwoIndex = 0 # Indexes strand two to get the char value
    
    strandOne.each_char do |c|
      if !strandTwo[strandTwoIndex].eql? c
        count = count + 1
      end
      strandTwoIndex = strandTwoIndex + 1
    end

    return count
  end
end
