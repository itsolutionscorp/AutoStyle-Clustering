#!/usr/bin/ruby

class Hamming
    def compute(a, b)
        distance = 0
        size = [a.length, b.length].min
        size.times do |i|
            distance += (a[i] == b[i])?0:1
        end
        distance
    end
end
