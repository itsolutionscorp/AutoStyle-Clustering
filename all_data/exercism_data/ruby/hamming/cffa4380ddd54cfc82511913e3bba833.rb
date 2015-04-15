#Hamming class for calculating hamming difference between two genetic sequences
#Used by hamming_test.rb
#For exercism.io

class Hamming
  def self.compute(strand_a, strand_b)
    #Hamming difference not defined for strands of differing lengths, return nil
    if (strand_a.length != strand_b.length)
      return nil
    end

    hamming_difference = 0
    #iterate through strand_a with index idx and compare to strand_b; increment hamming_difference if needed
    strand_a.length.times do |idx|
      if strand_a[idx] != strand_b[idx]
        hamming_difference += 1
      end
    end
    return hamming_difference
  end
end
