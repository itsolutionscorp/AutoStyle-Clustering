#
# Utility class to calculate hamming length
#

class Hamming
  def self.compute(strand_1, strand_2)
    raise TypeError unless (strand_1.is_a? String) && (strand_2.is_a? String)
    
    hamming = 0

    if strand_1.length > strand_2.length
      strand_1, strand_2 = strand_2, strand_1
    end

    strand_1.each_char.with_index do |char, index|
      hamming += 1 unless char == strand_2[index]
    end

    hamming
  end
end
