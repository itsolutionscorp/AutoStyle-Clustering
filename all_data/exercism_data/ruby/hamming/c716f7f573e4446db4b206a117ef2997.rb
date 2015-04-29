require 'pry'

class Hamming
  def self.compute(strand_a, strand_b)
    result = 0

    comparisons = 0
    if strand_a.length < strand_b.length
      comparisons = strand_a.length
    else
      comparisons = strand_b.length
    end

    (0..comparisons - 1).each do |i|
      if strand_a[i] != strand_b[i]
        result += 1
      end
    end

    return result
  end
end
