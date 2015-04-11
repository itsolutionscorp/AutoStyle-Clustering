#!/usr/bin/env ruby
# hamming test

class Hamming
  
  def self.compute(strand_a, strand_b)
    a = strand_a.split('')
    b = strand_b.split('')
    length_diff = 0

    if a.length > b.length
      length_diff = (a.length - b.length) + 1
      a = a[0..-length_diff]
    elsif b.length > a.length
      length_diff = (b.length - a.length) + 1
      b = b[0..-length_diff]
    end

    hamming_distance = 0
    for i in 0..a.length
      if a[i] != b[i]
        hamming_distance = hamming_distance + 1
      end
    end
    
    return hamming_distance
  end

end
