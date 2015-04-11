#!/usr/bin/env ruby


class Hamming
    def self.compute(sequence_a, sequence_b)
        length = [sequence_a.length, sequence_b.length].min
        distance = 0
        for i in 0..length-1
            distance += 1 unless sequence_a[i] == sequence_b[i]
        end
        distance
    end
end
