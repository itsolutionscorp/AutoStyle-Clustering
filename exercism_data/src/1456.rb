#!/usr/bin/env ruby

class Hamming
  def compute(strand1, strand2)
    common_length = [strand1.length, strand2.length].min
    (0...common_length).count do |i|
      strand1[i] != strand2[i]
    end
  end
end
