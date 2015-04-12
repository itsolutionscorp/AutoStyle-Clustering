require 'pry-nav'

class Hamming
  def compute(strand_1, strand_2)
    return 0 if strand_1 == strand_2

    length = strand_1.length
    distance = 0

    (1..length).each do |char|
      distance += 1 if strand_1[char-1] != strand_2[char-1]
    end

    return distance
  end
end
