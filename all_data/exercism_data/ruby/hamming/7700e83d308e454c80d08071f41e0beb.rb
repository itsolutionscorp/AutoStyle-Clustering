#! /usr/bin/env ruby
require 'rubygems'

class Hamming
  
  def self.compute(strand1,strand2)
    nucleotides1 = strand1.chars
    nucleotides2 = strand2.chars
    hams = 0
    (0...nucleotides1.count).each do |i|
      hams += 1 if nucleotides1[i] != nucleotides2[i]
    end
    hams
  end

end
