#!/usr/bin/env ruby


class Hamming

  def self.compute(strand1, strand2)
    distance = 0
    strand1.size.times do |index|
      distance += 1 unless strand1[index] == strand2[index]
    end
    distance
  end

end
