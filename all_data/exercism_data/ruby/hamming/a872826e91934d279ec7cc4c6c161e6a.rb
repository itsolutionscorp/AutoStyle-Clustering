# new iteration to test error

class Hamming
  def self.compute(strand1, strand2)
    hamming = 0

    def self.find_length(strand)
      strand.length - 1
    end

    if strand2.length > strand1.length
      length = find_length(strand1)
    else
      length = find_length(strand2)
    end


    (0..length).each do |l|
      if strand1[l] != strand2[l]
        hamming += 1
      elsif strand1 == strand2
        hamming = 0
      end
    end
    hamming
  end
end
