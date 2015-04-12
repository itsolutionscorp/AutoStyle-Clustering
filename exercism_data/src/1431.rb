#!/usr/bin/env ruby

class Hamming
    def compute(strandA, strandB)
        distance = 0
        (0..[strandA.length, strandB.length].min - 1).each do |index|
            distance = distance + 1 if strandA[index] != strandB[index]
        end
        distance
    end
end
