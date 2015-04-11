#! /usr/bin/env ruby

class Hamming
  def self.compute(seq1, seq2)
    arry1 = seq1.to_s.split("")
    arry2 = seq2.to_s.split("")

    length = [arry1.length, arry2.length].min - 1
    sum = 0

    (0..length).each do |i|
      sum += 1 unless arry1[i] == arry2[i]
    end

    sum
  end
end
