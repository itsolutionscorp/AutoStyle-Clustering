#!/usr/bin/ruby

class Hamming
    def compute(s, t)
        $index = 0
        distance = 0
        length = t.length

        if s.length < t.length
            length = s.length
        end

        until $index == length do
            if s[$index] != t[$index]
                distance += 1
            end
            $index += 1;
        end
        return distance
    end
end

Hamming = Hamming.new()
