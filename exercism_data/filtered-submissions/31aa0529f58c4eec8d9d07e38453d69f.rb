#
# Utility class to calculate hamming length
#

class Hamming
  def compute(strand_1, strand_2)
    hamming = 0

    if (strand_1.is_a? String) && (strand_2.is_a? String)
      if strand_1.length > strand_2.length
        strand_1, strand_2 = strand_2, strand_1
      end

      strand_1.each_char.with_index do |c, i|
        hamming += 1 if c != strand_2[i]
      end
    end

    hamming
  end
end
