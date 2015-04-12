#!/usr/bin/env ruby
# Compute Hamming distance given two nucleotide sequences of equal length

class Hamming

  def compute(seq1, seq2)
    dist = 0
    index = 0
    seq1 = seq1[0..(seq2.length - 1) ] if seq1.length > seq2.length
    seq1.each_char do |nucleotide|
      dist += 1 unless nucleotide == seq2[index]
      index += 1
    end
    return dist
  end

end
