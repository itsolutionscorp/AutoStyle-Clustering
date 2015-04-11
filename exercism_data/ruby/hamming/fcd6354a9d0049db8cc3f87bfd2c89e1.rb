#!/usr/bin/env ruby
#
require 'pry'
require 'byebug'
class Hamming
  def initialize
  end

  def self.compute(strand_1, strand_2)
    raise "DNA strands must have the same length" if (strand_1.length != strand_2.length)
    hamming_distance = 0
    strand_1.scan(/\w/).zip(strand_2.scan(/\w/)).each do |dna1, dna2|
      hamming_distance +=1 if (dna1 != dna2)
    end
    return hamming_distance
  end
end
