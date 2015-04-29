#!/usr/bin/env ruby

class Hamming
  def self.compute(dna, dnb)
    distance = 0

    (0..dna.length).each do |i|
      if (dna[i] != dnb[i]) && (!dna[i].nil?) && (!dnb[i].nil?)
        distance = distance + 1
      end
    end

    distance
  end
end
