class DNATest < MiniTest::Unit::TestCase
  class DNA
    def initialize(strand) #initializes the original strand
      @strand = strand
    end
    
    def hamming_distance(sample) # compares it to sample
      0 if sample.empty? # returns 0 if sample DNA is empty
      @strand = split(@strand) # splits the orginal DNA strand into an array
      sample = split(sample)  # splits the sample DNA strand into an array
      @strand = @strand.take(sample.length) # if the original strand is longer, ignores the rest of the strand
      @strand.each_with_index.count{|item, index| item != sample[index]} # returns number of different elements  
    end
    
    def split(string)
      string.split('')
    end
  end
end
