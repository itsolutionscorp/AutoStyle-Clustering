class Hamming

  class << self

    def compute(strand1,strand2)
      distance = 0
      shortestLength = shortestStrandLength(strand1,strand2)
      (0...(shortestLength)).each do |i|
        distance += 1 if strand1[i] != strand2[i]
      end
      distance
    end

    def shortestStrandLength(strand1,strand2)
      [strand1.length, strand2.length].min
    end

  end

end
