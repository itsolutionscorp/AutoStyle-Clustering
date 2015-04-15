# compute the Hamming difference between two strands of DNA

class Hamming

  def self.compute (strand1, strand2)
    length = [strand1.length, strand2.length].min
    diff = 0

    length.times do |i|
      if strand1[i].casecmp(strand2[i]) != 0
        diff += 1
      end
    end

    diff
  end

end
